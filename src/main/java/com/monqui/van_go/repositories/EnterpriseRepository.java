package com.monqui.van_go.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monqui.van_go.entities.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {

	List<Enterprise> findByActiveTrue();
	
    Optional<Enterprise> findByEmail(String email);

}