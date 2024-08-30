package com.pm.pm.controller;

import java.io.IOException;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.model.ProjectDTO;
import com.pm.cpo.repository.CPORepository;
import com.pm.cpo.repository.ProjectRepository;
import com.pm.pm.model.ProjectManagerDTO;
import com.pm.pm.services.PMService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/pm")
public class PMController {
	
	@Autowired
	CPORepository cpoRepo;
	
	@Autowired
	ProjectRepository projectRepo;
	
	@Autowired
	PMService pms;
	
	@PostMapping(path = "/add")
	public ProjectManagerDTO addProjectManager(
	    @RequestParam("name") String name,
	    @RequestParam("email") String email,
	    @RequestParam("password") String password,
	    @RequestParam("phone") String phone,
	    @RequestParam("position") String position,
	    @RequestParam("status") String status,
	    @RequestParam(value = "img", required = false) MultipartFile img,
	    @RequestParam("cpo") String cpo,
	    @RequestParam("projectId") String projectId
	) throws ParseException {

	    ProjectManagerDTO pmdto = new ProjectManagerDTO();
	    pmdto.setName(name);
	    pmdto.setEmail(email);
	    pmdto.setPassword(password);
	    pmdto.setPhone(Long.parseLong(phone));
	    pmdto.setPosition(position);
	    pmdto.setStatus(status);
	    
	    ChiefProductionOfficerDTO cpoObject = cpoRepo.findById(Long.parseLong(cpo)).orElse(null);
	    ProjectDTO project = projectRepo.findById(Long.parseLong(projectId)).orElse(null);
	    
	    pmdto.setCpo(cpoObject);
	    pmdto.setProject(project);
	    
	    if (img != null && !img.isEmpty()) {
	        try {
				pmdto.setImg(img.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    if (project != null && cpoObject != null) {
	        pms.projectManagerAdd(pmdto);
	        return pmdto;
	    }
	    
	    return null;
	}
	
	@GetMapping(path = "/get/all")
	public List<ProjectManagerDTO> getAllProjectAndPM(){
		List<ProjectManagerDTO> pmList = pms.getAllProjectManager();
		return pmList;
	}
	
	
	@GetMapping(path = "/login/{email}/{password}")
	public ProjectManagerDTO login(@PathVariable("email")  String email,@PathVariable("password") String password) {
		ProjectManagerDTO pm = pms.login(email, password);
		return pm;
		
	}
	
	@PutMapping(path = "/logout/{id}")
	public ProjectManagerDTO logout(@PathVariable("id") String id) {
		long tid = Long.parseLong(id);
		return pms.logout(tid);
	}
	
	@GetMapping(path = "/get")
	public ProjectManagerDTO getById(@RequestParam("id") String id) {
	    long tid = Long.parseLong(id);
	    return pms.getPMbyId(tid);
	}


}
