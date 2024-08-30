package com.pm.cpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.cpo.model.CPODetailsDTO;
import com.pm.cpo.model.ChiefProductionOfficerDTO;

@Repository
public interface CPODetails extends JpaRepository<CPODetailsDTO, Long> {
	CPODetailsDTO findByCpo(ChiefProductionOfficerDTO cpo);
}
