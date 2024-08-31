package com.pm.pm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.pm.model.ProjectManagerDTO;
import com.pm.pm.model.TeamMember;
import com.pm.pm.repository.ProjectManagerRepository;
import com.pm.pm.services.TeamMemberService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/pm/control/team")
public class PMControlTeamMemberController {
	
	@Autowired
	ProjectManagerRepository pmr;
	
	@Autowired
	TeamMemberService tms;
	
	@PostMapping(path = "/add")
	public TeamMember addTeamMember(
			@RequestParam("pmid") String pmid,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("phone") String phone,
			@RequestParam("position") String position,
			@RequestParam(value = "img", required = false) MultipartFile img
			) {
			
			TeamMember team = new TeamMember();
			
			team.setName(name);
			team.setEmail(email);
			team.setPassword(password);
			try {
				team.setPhone(Long.parseLong(phone));
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
			team.setPosition(position);
			try {
				team.setImg(img.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			team.setActive("inactive");
			team.setStatus(null);
			
			ProjectManagerDTO pmdto = pmr.findById(Long.parseLong(pmid)).orElse(null);
			
			if(pmdto != null) {
				team.setPm(pmdto);
				TeamMember tm = tms.addMember(team);
				return tm;
			}else {
				System.out.println("Pm is null");
				return null;
			}
	
	}
	
	@GetMapping(path = "/get/{pmid}")
	public List<TeamMember> getAllByPMid(@PathVariable("pmid") String pmid) {
		
		ProjectManagerDTO pmdto = pmr.findById(Long.parseLong(pmid)).orElse(null);
		
		if(pmdto != null) {
			List<TeamMember> tm = tms.getTeamByPM(pmdto);
			for(TeamMember team : tm) {
				team.setPm(null);
			}
			
			if(!tm.isEmpty()) {
				return tm;
			}else {
				return tm=null;
			}
		}
		return null;	

	}
	
	@PutMapping(path = "/status/block/{tmid}")
	public ResponseEntity<Map<String, String>> statusUpdateBlock(@PathVariable("tmid") String teamId) {
	    long teamIdLong;
	    try {
	        teamIdLong = Long.parseLong(teamId);
	    } catch (Exception e) {
	        teamIdLong = 0;
	    }
	    
	    String value = tms.statusUpdatedBlock(teamIdLong);
	    if (value != null) {
	        Map<String, String> response = new HashMap();
	        response.put("status", "success");
	        return ResponseEntity.ok(response);
	    }
	    
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@PutMapping(path = "/status/unblock/{tmid}")
	public ResponseEntity<Map<String, String>> statusUpdateUnblock(@PathVariable("tmid") String teamId) {
	    long teamIdLong;
	    try {
	        teamIdLong = Long.parseLong(teamId);
	    } catch (Exception e) {
	        teamIdLong = 0;
	    }
	    
	    String value = tms.statusUpdatedunblock(teamIdLong);
	    if (value != null) {
	        Map<String, String> response = new HashMap<>();
	        response.put("status", "success");
	        return ResponseEntity.ok(response);
	    }
	    
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	
	
}
