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

import com.monqui.van_go.entities.Driver;
import com.monqui.van_go.entities.Enterprise;
import com.monqui.van_go.entities.Vehicle;
import com.monqui.van_go.services.EnterpriseService;

@RestController
@RequestMapping(value = "/enterprises")
public class EnterpriseResource {

	@Autowired
	private EnterpriseService service;

	@GetMapping
	public ResponseEntity<List<Enterprise>> findAll() {

		List<Enterprise> list = service.findAll();

		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Enterprise> findById(@PathVariable Long id) {
		Enterprise enterprise = service.findById(id);
		return ResponseEntity.ok().body(enterprise);
	}

	@PostMapping
	public ResponseEntity<Enterprise> insert(@RequestBody Enterprise enterprise) {
		enterprise = service.insert(enterprise);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(enterprise.getId())
				.toUri();
		return ResponseEntity.created(uri).body(enterprise);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/{id}/activate")
	public ResponseEntity<Enterprise> activate(@PathVariable Long id) {
		Enterprise enterprise = service.activate(id);
		return ResponseEntity.ok().body(enterprise);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Enterprise> update(@PathVariable Long id, @RequestBody Enterprise enterprise) {
		enterprise = service.update(id, enterprise);
		return ResponseEntity.ok().body(enterprise);
	}

	@GetMapping("/{id}/vehicles")
	public List<Vehicle> getVehicles(@PathVariable Long id) {
		return service.getVehiclesByEnterprise(id);
	}

	@GetMapping("/{id}/drivers")
	public List<Driver> getDrivers(@PathVariable Long id) {
		return service.getDriversByEnterprise(id);
	}

	@PostMapping("/{id}/driver")
	public ResponseEntity<Driver> addDriver(@PathVariable Long id, @RequestBody Driver driver) {
		Driver newDriver = service.addDriverToEnterprise(id, driver);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{driverId}").buildAndExpand(newDriver.getId())
				.toUri();
		return ResponseEntity.created(uri).body(newDriver);
	}

	@PostMapping("/{id}/vehicle")
	public ResponseEntity<Vehicle> addVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
		Vehicle newVehicle = service.addVehicleToEnterprise(id, vehicle);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{vehicleId}")
				.buildAndExpand(newVehicle.getId()).toUri();
		return ResponseEntity.created(uri).body(newVehicle);
	}

	@GetMapping("/validate")
	public ResponseEntity<Boolean> isValidEnterprise(@RequestParam String email, @RequestParam String password) {
		boolean isValid = service.isValidEnterprise(email, password);
		return ResponseEntity.ok(isValid);
	}

}
