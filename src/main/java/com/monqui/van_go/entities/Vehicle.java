package com.monqui.van_go.entities;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vehicle")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String plate;
	private String color;
	private String model;
	private int vehicle_year;
	private int quantityPlaces;
	private boolean active = true;

	private final char typeEntity = 'V';

	@ManyToOne
	@JoinColumn(name = "enterprise_id")
	@JsonBackReference
	private Enterprise enterprise;

	public Vehicle() {
	}

	public Vehicle(Long id, String plate, String color, String model, int vehicle_year, int quantityPlaces,
			Enterprise enterprise) {
		this.id = id;
		this.plate = plate;
		this.color = color;
		this.model = model;
		this.vehicle_year = vehicle_year;
		this.enterprise = enterprise;
		this.quantityPlaces = quantityPlaces;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantityPlaces() {
		return quantityPlaces;
	}

	public void setQuantityPlaces(int quantityPlaces) {
		this.quantityPlaces = quantityPlaces;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public int getVehicle_year() {
		return vehicle_year;
	}

	public void setVehicle_year(int vehicle_year) {
		this.vehicle_year = vehicle_year;
	}

	public char getTypeEntity() {
		return typeEntity;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		return Objects.equals(id, other.id);
	}
}
