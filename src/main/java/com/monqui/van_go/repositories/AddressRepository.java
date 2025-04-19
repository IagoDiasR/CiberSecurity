package com.monqui.van_go.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monqui.van_go.entities.location.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
