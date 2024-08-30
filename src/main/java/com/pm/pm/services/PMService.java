package com.pm.pm.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.pm.model.ProjectManagerDTO;
import com.pm.pm.repository.ProjectManagerRepository;


@Service
public class PMService {
	
	@Autowired
	ProjectManagerRepository pmr;
	
	public ProjectManagerDTO projectManagerAdd(ProjectManagerDTO pmdto) {
		try {
			pmr.save(pmdto);
			return pmdto;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
	}
	
	public List<ProjectManagerDTO> getAllProjectManager() {
		List<ProjectManagerDTO> listPmr= pmr.findAll();
		if(listPmr.isEmpty()) {
			return null;
		}
		return listPmr;
	}
	
	//Project Manager Login
	public ProjectManagerDTO login(String email, String pass) {
		Optional<ProjectManagerDTO> pm = pmr.findByEmailAndPassword(email, pass);
		
		if(pm.isPresent()) {
			ProjectManagerDTO pmdto = pm.get();
			pmdto.setStatus("active");
			pmr.save(pmdto);
			return pmdto;
		}
		
		return null;
	}
	
	//project manager logout
	public ProjectManagerDTO logout(long id) {
		ProjectManagerDTO pm = pmr.findById(id).orElse(null);
		if(pm != null) {
			pm.setStatus("inactive");
			pmr.save(pm);
			return pm;
		}
		return null;
	}
	
	public ProjectManagerDTO getPMbyId(long id) {
		return pmr.findById(id).orElse(null);
	}
	
}
