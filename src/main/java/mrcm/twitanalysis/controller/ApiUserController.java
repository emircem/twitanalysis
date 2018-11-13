package mrcm.twitanalysis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import mrcm.twitanalysis.service.ApiUserService;
import mrcm.twitanalysis.service.dto.ApiUserDto;


@RestController
public class ApiUserController {
	
	@Autowired
	private ApiUserService apiUserService;
	
	
	@PostMapping("/apiusers")
	public ResponseEntity<ApiUserDto> createApiUser(@RequestBody ApiUserDto apiUserDto) {
		apiUserDto.setRole("ROLE_USER");
		apiUserService.save(apiUserDto);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(apiUserDto.getId())
				.toUri();
				
		return ResponseEntity.created(location).body(apiUserDto);
	}
	
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/apiusers")
	public List<ApiUserDto> readApiUsers() {
		return apiUserService.findAll();
	}

}
