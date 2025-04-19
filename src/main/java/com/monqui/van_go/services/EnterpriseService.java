package com.monqui.van_go.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monqui.van_go.entities.Driver;
import com.monqui.van_go.entities.Enterprise;
import com.monqui.van_go.entities.Vehicle;
import com.monqui.van_go.repositories.DriverRepository;
import com.monqui.van_go.repositories.EnterpriseRepository;
import com.monqui.van_go.repositories.VehicleRepository;
import com.monqui.van_go.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EnterpriseService {

	@Autowired
	private EnterpriseRepository repository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private DriverRepository driverRepository;
	
	private final PasswordEncoder passwordEncoder;

    public EnterpriseService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder; 
    }
    
	public List<Enterprise> findAll() {
		return repository.findByActiveTrue();
	}

	public Enterprise findById(Long id) {

		Optional<Enterprise> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Enterprise insert(Enterprise enterprise) {
		enterprise.setPassword(passwordEncoder.encode(enterprise.getPassword()));
		return repository.save(enterprise);
	}

	public void delete(Long id) {
		Enterprise enterprise = findById(id);
		enterprise.setActive(false);

		List<Driver> listDrivers = enterprise.getDrivers();
		List<Vehicle> listVehicles = enterprise.getVehicles();

		// Inativar Drivers associados a Enterprise:
		for (Driver driver : listDrivers) {
			driver.setActive(false);
			driverRepository.save(driver);
		}
		// Inativar Vehicles associados a Enterprise:
		for (Vehicle vehicle : listVehicles) {
			vehicle.setActive(false);
			vehicleRepository.save(vehicle);
		}

		repository.save(enterprise);
	}

	public Enterprise activate(Long id) {
		Enterprise enterprise = findById(id);
		enterprise.setActive(true);
		return update(id, enterprise);
	}

	public Enterprise update(Long id, Enterprise enterprise) {
		try {
			Enterprise entity = repository.getReferenceById(id);
			updateData(entity, enterprise);
			return repository.save(entity);
		} catch (EntityNotFoundException exception) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Enterprise entity, Enterprise enterprise) {
		// Padrão do User (Retirado Age)
		entity.setName(enterprise.getName());
		entity.setEmail(enterprise.getEmail());
		entity.setPassword(enterprise.getPassword());
		entity.setTelephone(enterprise.getTelephone());
		entity.setAddress(enterprise.getAddress());

		// Variáveis da empresa
		entity.setDrivers(enterprise.getDrivers());
		entity.setVehicles(enterprise.getVehicles());
		entity.setActive(enterprise.getActive());
	}

	public List<Vehicle> getVehiclesByEnterprise(Long enterpriseId) {
		return vehicleRepository.findByEnterpriseId(enterpriseId);
	}

	public List<Driver> getDriversByEnterprise(Long enterpriseId) {
		return driverRepository.findByEnterpriseId(enterpriseId);
	}

	public Driver addDriverToEnterprise(Long enterpriseId, Driver driver) {
		Enterprise enterprise = findById(enterpriseId);
		driver.setEnterprise(enterprise);
		driver.setPassword(passwordEncoder.encode(driver.getPassword()));
		return driverRepository.save(driver);
	}

	public Vehicle addVehicleToEnterprise(Long enterpriseId, Vehicle vehicle) {
		Enterprise enterprise = findById(enterpriseId);
		vehicle.setEnterprise(enterprise);
		return vehicleRepository.save(vehicle);
	}

	public boolean isValidEnterprise(String email, String password) {
	    Optional<Enterprise> enterpriseOptional = repository.findByEmail(email);

	    if (enterpriseOptional.isPresent()) {
	        Enterprise enterprise = enterpriseOptional.get();
	        return passwordEncoder.matches(password, enterprise.getPassword());
	    }
	    return false;
	}

}
