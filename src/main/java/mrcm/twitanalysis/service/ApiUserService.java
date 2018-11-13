package mrcm.twitanalysis.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import mrcm.twitanalysis.db.entity.ApiUser;
import mrcm.twitanalysis.db.repository.ApiUserRepository;
import mrcm.twitanalysis.service.dto.ApiUserDto;


@Service
@Transactional
public class ApiUserService implements UserDetailsService {

	@Autowired
	private ApiUserRepository apiUserRepo;
	
	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public void save(ApiUserDto apiUserDto) {
		ApiUser apiUser = mapper.map(apiUserDto, ApiUser.class);
		apiUser.setPassword(passwordEncoder.encode(apiUserDto.getPassword()));
		apiUserRepo.save(apiUser);
		mapper.map(apiUser, apiUserDto);
	}
	
	public List<ApiUserDto> findAll() {
		List<ApiUser> list = apiUserRepo.findAll();
		
		return list.stream().map(s -> mapper.map(s, ApiUserDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApiUser apiUser = apiUserRepo.findByUsername(username);
		if (apiUser == null)
			throw new UsernameNotFoundException("username=" + username);
		
		return apiUser;
	}

}
