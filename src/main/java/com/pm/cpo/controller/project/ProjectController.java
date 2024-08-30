package com.pm.cpo.controller.project;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.model.ProjectDTO;
import com.pm.cpo.repository.CPORepository;
import com.pm.cpo.services.ProjectServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/project")
public class ProjectController {

    @Autowired
    private ProjectServices ps;
    
    @Autowired
    private CPORepository cporop;

    @PostMapping(path ="/save")
    public ProjectDTO saveProject(
            @RequestParam("id") String id,
            @RequestParam("pname") String projectName,
            @RequestParam("pdescription") String description,
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr,
            @RequestParam("status") String status,
            @RequestParam("client") String client,
            @RequestParam(value = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(value = "projectDocuments", required = false) MultipartFile projectDocuments) throws ParseException {

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setPname(projectName);
        projectDTO.setDescription(description);

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);

        projectDTO.setStartdate(startDate);
        projectDTO.setEnddate(endDate);
        projectDTO.setStatus(status);
        projectDTO.setClient(client);

        ChiefProductionOfficerDTO cpo = cporop.findById(Long.parseLong(id)).orElse(null);
        projectDTO.setCpo(cpo);

        try {
            if (profileImage != null) {
                projectDTO.setImg(profileImage.getBytes());
            }
            if (projectDocuments != null) {
                projectDTO.setPdf(projectDocuments.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ps.addProject(projectDTO);
    }
    
    @GetMapping(path = "get/all", produces = {"application/json"})
    public List<ProjectDTO> getAllProjects(){
    	return ps.getAllProject();
    }
    
    @PutMapping(path = "status/update")
    public ProjectDTO updateStatus(@RequestParam("id") long id,@RequestParam("status") String status) {
    	return ps.updateStatus(id, status);
    }
    
    
}
