package com.pm.cpo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.cpo.model.ChiefProductionOfficerDTO;

@Repository
public interface CPORepository extends JpaRepository<ChiefProductionOfficerDTO, Long> {
	
	Optional<ChiefProductionOfficerDTO> findByEmailAndPassword(String email, String password);

}
