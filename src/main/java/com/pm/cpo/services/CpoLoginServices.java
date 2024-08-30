package com.pm.cpo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.cpo.model.CPODetailsDTO;
import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.repository.CPODetails;
import com.pm.cpo.repository.CPORepository;

@Service
public class CpoLoginServices {
	
	@Autowired
	private CPORepository cpoRepository;
	
	@Autowired
	private CPODetails cpoDetailRepository;
	
	public ChiefProductionOfficerDTO signServices(ChiefProductionOfficerDTO cpo, CPODetailsDTO cpoDetail) {
		try {
			cpoRepository.save(cpo);
			try {
				cpoDetailRepository.save(cpoDetail);
				return cpo;
			} catch (Exception e) {
				System.out.println("cpo details error "+e);
			}
		}catch (Exception e) {
			System.out.println("cpo save error "+e);
		}
		return null;
	}
	
	public CPODetailsDTO loginServices(String email, String pass) {
		Optional<ChiefProductionOfficerDTO> cpo =cpoRepository.findByEmailAndPassword(email, pass);
		
		if(cpo.isPresent()) {
			ChiefProductionOfficerDTO cpoObject = cpo.get();
			cpoObject.setActive("active");
			cpoRepository.save(cpoObject);
			CPODetailsDTO cpoDetails = cpoDetailRepository.findByCpo(cpoObject);
			if(cpoDetails != null) {
				return cpoDetails;
			}else {
				cpoDetails = null;
				return cpoDetails;
			}
		}else {
			return null;
		}
	}
	
	public ChiefProductionOfficerDTO logoutServices(long id) {
		ChiefProductionOfficerDTO cpo = cpoRepository.findById(id).orElse(null);
		if(cpo != null) {
			System.out.println(cpo);
			cpo.setActive("inactive");
			cpoRepository.save(cpo);
		}
		return cpo;
	}
	
}
