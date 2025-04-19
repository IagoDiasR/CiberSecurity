package com.monqui.van_go.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monqui.van_go.entities.Vehicle;
import com.monqui.van_go.repositories.VehicleRepository;
import com.monqui.van_go.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	public List<Vehicle> findAll() {
		return repository.findAll();
	}

	public Vehicle findById(Long id) {

		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Vehicle insert(Vehicle vehicle) {
		return repository.save(vehicle);
	}

	public void delete(Long id) {
		Vehicle vehicle = findById(id);
		vehicle.setActive(false);
		repository.save(vehicle);
	}

	public Vehicle update(Long id, Vehicle vehicle) {
		try {
			Vehicle entity = repository.getReferenceById(id);
			updateData(entity, vehicle);
			return repository.save(entity);
		} catch (EntityNotFoundException exception) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Vehicle entity, Vehicle vehicle) {
		entity.setPlate(vehicle.getPlate());
		entity.setColor(vehicle.getColor());
		entity.setModel(vehicle.getModel());
		entity.setVehicle_year(vehicle.getVehicle_year());
		entity.setQuantityPlaces(vehicle.getQuantityPlaces());
	}

}
