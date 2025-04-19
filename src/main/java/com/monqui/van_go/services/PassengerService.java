package com.monqui.van_go.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.monqui.van_go.entities.Passenger;
import com.monqui.van_go.repositories.PassengerRepository;
import com.monqui.van_go.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository repository;
	
    private final PasswordEncoder passwordEncoder;

    public PassengerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder; 
    }

	public List<Passenger> findAll() {
		return repository.findByActiveTrue();
	}

	public Passenger findById(Long id) {

		Optional<Passenger> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Passenger insert(Passenger passenger) {
		passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
		return repository.save(passenger);
	}

	public void delete(Long id) {
		Passenger passenger = findById(id);
		passenger.setActive(false);
		repository.save(passenger);
	}

	public Passenger activate(Long id) {
		Passenger passenger = findById(id);
		passenger.setActive(true);
		return update(id, passenger);
	}

	public Passenger update(Long id, Passenger passenger) {
		try {
			Passenger entity = repository.getReferenceById(id);
			updateData(entity, passenger);
			return repository.save(entity);
		} catch (EntityNotFoundException exception) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Passenger entity, Passenger passenger) {
		// Padrão do User
		entity.setName(passenger.getName());
		entity.setAge(passenger.getAge());
		entity.setEmail(passenger.getEmail());
		entity.setPassword(passenger.getPassword());
		entity.setTelephone(passenger.getTelephone());
		entity.setAddress(passenger.getAddress());

		// Variáveis do passageiro
		entity.setTimeline(passenger.getTimeline());
		entity.setActive(passenger.getActive());

	}

	public boolean isValidPassenger(String email, String password) {
	    Optional<Passenger> passengerOptional = repository.findByEmail(email);

	    if (passengerOptional.isPresent()) {
	        Passenger passenger = passengerOptional.get();
	        return passwordEncoder.matches(password, passenger.getPassword());
	    }
	    return false;
	}

}
