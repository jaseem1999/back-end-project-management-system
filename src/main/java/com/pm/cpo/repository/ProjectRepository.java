package com.pm.cpo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.cpo.model.ProjectDTO;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectDTO, Long> {

}
