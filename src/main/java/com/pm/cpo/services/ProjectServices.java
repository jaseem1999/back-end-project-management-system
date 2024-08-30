package com.pm.cpo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.cpo.model.ProjectDTO;
import com.pm.cpo.repository.ProjectRepository;

@Service
public class ProjectServices {
	
	@Autowired
	ProjectRepository projectRepository;
	
	public ProjectDTO addProject(ProjectDTO projectDto) {
		projectRepository.save(projectDto);
		return projectDto;
	}
	
	public List<ProjectDTO> getAllProject() {
		List<ProjectDTO> listProject = projectRepository.findAll();
		if(listProject.isEmpty()) {
			return null;
		}
		return listProject;
	}
	
	public ProjectDTO updateStatus(long tid, String status) {
		ProjectDTO projectdto = projectRepository.findById(tid).orElse(null);
		projectdto.setStatus(status);
		projectRepository.save(projectdto);
		
		return projectdto;
	}
}

