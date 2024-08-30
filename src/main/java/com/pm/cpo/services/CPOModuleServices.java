package com.pm.cpo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.cpo.model.CPODetailsDTO;
import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.repository.CPODetails;

@Service
public class CPOModuleServices {
	@Autowired
	private CPODetails cpod;
	
	public CPODetailsDTO getByCpo(ChiefProductionOfficerDTO cpo) {
		return cpod.findByCpo(cpo);
	}
}
