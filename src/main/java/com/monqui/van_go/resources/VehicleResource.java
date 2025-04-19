package com.monqui.van_go.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.monqui.van_go.entities.Vehicle;
import com.monqui.van_go.services.VehicleService;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleResource {
	
	@Autowired
	private VehicleService service;
	
	@GetMapping
	public ResponseEntity<List<Vehicle>> findAll() {
		
		List<Vehicle> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Vehicle> findById(@PathVariable Long id){
		Vehicle vehicle = service.findById(id);
		return ResponseEntity.ok().body(vehicle);
	}
	
	@PostMapping
	public ResponseEntity<Vehicle> insert(@RequestBody Vehicle vehicle){
		vehicle = service.insert(vehicle);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicle.getId()).toUri();
		return ResponseEntity.created(uri).body(vehicle);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody Vehicle vehicle){
		
		vehicle = service.update(id, vehicle);
		return ResponseEntity.ok().body(vehicle);
		
	}
	
	
	
}
