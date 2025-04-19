package com.monqui.van_go.entities;

import java.util.Objects;

import com.monqui.van_go.entities.location.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_passenger")
public class Passenger extends User {
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "timeline_id")
	private Timeline timeline;

	private boolean active = true;

	private final char typeEntity = 'P';

	public Passenger() {
	}

	public Passenger(Long id, String age, String name, String email, String password, String telephone, Address address,
			Timeline timeline) {
		super(id, age, name, email, password, telephone, address);
		this.timeline = timeline;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
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
