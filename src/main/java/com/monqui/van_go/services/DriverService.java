package com.monqui.van_go.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monqui.van_go.entities.Driver;
import com.monqui.van_go.entities.Vehicle;
import com.monqui.van_go.repositories.DriverRepository;
import com.monqui.van_go.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DriverService {

	@Autowired
	private DriverRepository repository;

	@Autowired
	private VehicleService vehicleService;
	
	private final PasswordEncoder passwordEncoder;

    public DriverService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder; 
    }
    
	public List<Driver> findAll() {
		return repository.findByActiveTrue();
	}

	public Driver findById(Long id) {

		Optional<Driver> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	// Desabilitado devido a RN de ser criado somente por empresa.
//	public Driver insert(Driver driver) {
//		return repository.save(driver);
//	}

	public void delete(Long id) {
		Driver driver = findById(id);
		driver.setActive(false);
		repository.save(driver);
	}

	public Driver activate(Long id) {
		Driver driver = findById(id);
		driver.setActive(true);
		return repository.save(driver);
	}

	public Driver update(Long id, Driver driver) {
		try {
			Driver entity = repository.getReferenceById(id);
			updateData(entity, driver);
			return repository.save(entity);
		} catch (EntityNotFoundException exception) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Driver entity, Driver driver) {
		// Padrão do User
		entity.setName(driver.getName());
		entity.setAge(driver.getAge());
		entity.setEmail(driver.getEmail());
		entity.setPassword(driver.getPassword());
		entity.setTelephone(driver.getTelephone());
		entity.setAddress(driver.getAddress());

		// Variáveis do motorista
		entity.setCnh(driver.getCnh());
		entity.setCnhCategory(driver.getCnhCategory());
		entity.setValidityCnh(driver.getValidityCnh());
		entity.setActive(driver.getActive());
		entity.setVehicle(driver.getVehicle());
	}

	public Driver assignVehicleToDriver(Long driverId, Long vehicleId) {
		Driver driver = findById(driverId);
		Vehicle vehicle = vehicleService.findById(vehicleId);
		driver.setVehicle(vehicle);
		return repository.save(driver);
	}

	public Driver removeVehicleFromDriver(Long driverId) {
		Driver driver = findById(driverId);
		driver.setVehicle(null);
		return repository.save(driver);
	}
	
	public boolean isValidDriver(String email, String password) {
	    Optional<Driver> driverOptional = repository.findByEmail(email);

	    if (driverOptional.isPresent()) {
	        Driver driver = driverOptional.get();
	        return passwordEncoder.matches(password, driver.getPassword());
	    }
	    return false;
	}
}
