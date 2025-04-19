package com.monqui.van_go.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.monqui.van_go.entities.Passenger;
import com.monqui.van_go.services.PassengerService;

@RestController
@RequestMapping(value = "/passengers")
public class PassengerResource {

	@Autowired
	private PassengerService service;

	@GetMapping
	public ResponseEntity<List<Passenger>> findAll() {

		List<Passenger> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Passenger> findById(@PathVariable Long id) {
		Passenger passenger = service.findById(id);
		return ResponseEntity.ok().body(passenger);
	}

	@PostMapping
	public ResponseEntity<Passenger> insert(@RequestBody Passenger passenger) {
		passenger = service.insert(passenger);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(passenger.getId())
				.toUri();
		return ResponseEntity.created(uri).body(passenger);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/activate")
	public ResponseEntity<Passenger> activate(@PathVariable Long id) {
		Passenger passenger = service.activate(id);
		return ResponseEntity.ok().body(passenger);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Passenger> update(@PathVariable Long id, @RequestBody Passenger passenger) {

		passenger = service.update(id, passenger);
		return ResponseEntity.ok().body(passenger);
	}

	@GetMapping("/validate")
	public ResponseEntity<Boolean> isValidPassenger(@RequestParam String email, @RequestParam String password) {
		boolean isValid = service.isValidPassenger(email, password);
		return ResponseEntity.ok(isValid);
	}

}
