package com.pm.pm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pm.pm.model.ProjectManagerDTO;
import com.pm.pm.model.TeamMember;
import com.pm.pm.repository.TeamMemberRepository;

@Service
public class TeamMemberService {
	
	@Autowired
	TeamMemberRepository tmr;
	
	
	public TeamMember addMember(TeamMember team)
	{
		tmr.save(team);
		return team;
	}
	
	public List<TeamMember> getTeamByPM(ProjectManagerDTO pmdto) {
		return tmr.findByPm(pmdto);
	}
	
	public String statusUpdatedBlock(long teamId) {
		TeamMember team =tmr.findById(teamId).orElse(null);
		
		if(team != null) {
			team.setStatus("block");
			tmr.save(team);
			return "success";
		}
		
		return null;
	}
	public String statusUpdatedunblock(long teamId) {
		TeamMember team =tmr.findById(teamId).orElse(null);
		
		if(team != null) {
			team.setStatus(null);
			tmr.save(team);
			return "success";
		}
		
		return null;
	}
}
