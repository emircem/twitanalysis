package mrcm.twitanalysis.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import mrcm.twitanalysis.db.entity.ApiUser;
import mrcm.twitanalysis.db.entity.Twit;
import mrcm.twitanalysis.db.repository.TwitRepository;
import mrcm.twitanalysis.service.dto.ApiUserDto;
import mrcm.twitanalysis.service.dto.TwitDto;


@Service
@Transactional
public class TwitService {
	
	@Autowired
	private TwitRepository twitRepo;
	
	@Autowired
	private MapperFacade mapper;
	
	
	public void save(TwitDto twitDto) {
		Twit twit = mapper.map(twitDto, Twit.class);
		twitRepo.save(twit);
		mapper.map(twit, twitDto);
	}
	
	public List<TwitDto> findAllByApiUserDto(ApiUserDto apiUserDto) {
		List<Twit> list = twitRepo.findAllByApiUser(mapper.map(apiUserDto, ApiUser.class));
		
		return list.stream().map(s -> mapper.map(s, TwitDto.class)).collect(Collectors.toList());
	}
	
	
	public List<TwitDto> findAll() {
		List<Twit> list = twitRepo.findAll();
		
		return list.stream().map(s -> mapper.map(s, TwitDto.class)).collect(Collectors.toList());
	}

}
