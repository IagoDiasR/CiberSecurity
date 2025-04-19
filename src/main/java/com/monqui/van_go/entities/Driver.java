package com.monqui.van_go.entities;

import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.monqui.van_go.entities.location.Address;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_driver")
public class Driver extends User {
	private static final long serialVersionUID = 1L;
	
	private String cnh;
	private String cnhCategory;
	private boolean active = true;

	private final char typeEntity = 'D'; 

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE) 
    private LocalDate validityCnh;
	
	@ManyToOne
    @JoinColumn(name = "enterprise_id") 
	@JsonBackReference
    private Enterprise enterprise;
	
	@OneToOne
	private Vehicle vehicle;
	
	
	

	public Driver() {
	}

	public Driver(Long id, String age, String name, String email, String password, String telephone, Address address, String cnh, String cnhCategory, LocalDate validityCnh, Enterprise enterprise, Vehicle vehicle) {
		super(id ,age, name, email, password, telephone, address);
		this.cnh = cnh;
		this.cnhCategory = cnhCategory;
		this.validityCnh = validityCnh;
		this.enterprise = enterprise;
		this.vehicle = vehicle;
	}

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public String getCnhCategory() {
		return cnhCategory;
	}

	public void setCnhCategory(String cnhCategory) {
		this.cnhCategory = cnhCategory;
	}

	public LocalDate getValidityCnh() {
		return validityCnh;
	}

	public void setValidityCnh(LocalDate validityCnh) {
		this.validityCnh = validityCnh;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public char getTypeEntity() {
		return typeEntity;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(super.getId());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Driver other = (Driver) obj;
		return Objects.equals(super.getId(), other.getId());
	}
}
