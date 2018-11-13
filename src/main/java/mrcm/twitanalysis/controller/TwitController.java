package mrcm.twitanalysis.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ma.glasnost.orika.MapperFacade;
import mrcm.twitanalysis.db.entity.ApiUser;
import mrcm.twitanalysis.service.TwitService;
import mrcm.twitanalysis.service.dto.ApiUserDto;
import mrcm.twitanalysis.service.dto.TwitDto;


@RestController
public class TwitController {

	@Autowired
	private TwitService twitService;
	
	@Autowired
	private MapperFacade mapper;
	
	
	@PostMapping("/twits")
	public ResponseEntity<TwitDto> createTwit(@RequestBody TwitDto twitDto) {
		ApiUser apiUser = (ApiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		twitDto.setApiUser(mapper.map(apiUser, ApiUserDto.class));
		twitService.save(twitDto);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(twitDto.getId())
				.toUri();
				
		return ResponseEntity.created(location).body(twitDto);
	}
	
	@GetMapping("/twits")
	public List<TwitDto> readTwits() {
		ApiUser apiUser = (ApiUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return twitService.findAllByApiUserDto(mapper.map(apiUser, ApiUserDto.class));
	}
	
}
