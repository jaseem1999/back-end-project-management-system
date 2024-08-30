package com.pm.pm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.pm.model.ProjectManagerDTO;



@Repository
public interface ProjectManagerRepository extends JpaRepository<ProjectManagerDTO, Long> {
	
	Optional<ProjectManagerDTO> findByEmailAndPassword(String email, String password);
}
