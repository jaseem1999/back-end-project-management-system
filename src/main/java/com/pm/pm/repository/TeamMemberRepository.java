package com.pm.pm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pm.pm.model.ProjectManagerDTO;
import com.pm.pm.model.TeamMember;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
	List<TeamMember> findByPm(ProjectManagerDTO pmdto);
}
