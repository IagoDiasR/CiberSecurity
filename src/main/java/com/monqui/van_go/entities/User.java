package com.monqui.van_go.entities;

import java.io.Serializable;
import java.util.Objects;

import com.monqui.van_go.entities.location.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String age;
	private String name;
	private String email;
	private String password;
	private String telephone;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

	public User() {
	}
	
	public User(Long id, String age, String name, String email, String password, String telephone, Address address) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.address = address;
	}
	
	//Criar empresa sem idade
	public User(Long id, String name, String email, String password, String telephone, Address address) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
