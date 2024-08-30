package com.pm.cpo.controller.login;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pm.cpo.model.CPODetailsDTO;
import com.pm.cpo.model.ChiefProductionOfficerDTO;
import com.pm.cpo.repository.CPODetails;
import com.pm.cpo.repository.CPORepository;
import com.pm.cpo.services.CPOModuleServices;
import com.pm.cpo.services.CpoLoginServices;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path="api/cpo/log")
public class LoginCpoController {
	
	@Autowired
	private CpoLoginServices cpoLoginServices; 
	
	//Post man checking 
	@Autowired
	private CPODetails cpoDetails;
	
	@Autowired
	private CPORepository cporep;
	
	@Autowired
	private CPOModuleServices cpoService;
	
	
	
	
	//Sign using the post man because Chief Production Officer default login
	@PostMapping(path = "/sign")
	public ChiefProductionOfficerDTO signupCPO(@RequestParam("email") String email,
			@RequestParam("pass") String pass,
			@RequestParam("status") String status, 
			@RequestParam("active") String active,
			@RequestParam("name") String name,
			@RequestParam("position") String position,
			@RequestParam("phone") long phone,
			@RequestParam("message")String message,
			@RequestParam("img")MultipartFile img) 
	{
		ChiefProductionOfficerDTO cpoDto = new ChiefProductionOfficerDTO(email, pass, status, active);		
		ChiefProductionOfficerDTO cpoTemp = new  ChiefProductionOfficerDTO();
		try {
			CPODetailsDTO cpoDetails = new CPODetailsDTO(name, position, message, img.getBytes(), cpoDto);
			cpoTemp =cpoLoginServices.signServices(cpoDto, cpoDetails);
		} catch (IOException e) {
			
			System.out.println("Error on sign controller");
			e.printStackTrace();
		}
		return cpoTemp;
	}
	
	
	//Post man checking 
	@GetMapping(path = "/get/cpo")
	public List<CPODetailsDTO> getCPO() {
		return cpoDetails.findAll();
	}
	
	
	@GetMapping(path = "/cpo/login/{email}/{password}")
	public CPODetailsDTO login(@PathVariable("email") String email, @PathVariable("password") String password) {
	    return cpoLoginServices.loginServices(email, password);
	}
	
	@PutMapping(path = "/cpo/logout/{id}")
	public ChiefProductionOfficerDTO logout(@PathVariable("id") long id) {
		ChiefProductionOfficerDTO cpo = cpoLoginServices.logoutServices(id);
		return cpo;
	}
	
	@GetMapping(path = "/get/id/{id}")
    public CPODetailsDTO getByCpo(@PathVariable("id") String id) {
		long tid = Long.parseLong(id);
		ChiefProductionOfficerDTO dto = cporep.findById(tid).orElse(null);
		if(dto != null) {
			return cpoService.getByCpo(dto);
		}
        return null;
    }

}
