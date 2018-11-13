package mrcm.twitanalysis.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrcm.twitanalysis.db.entity.ApiUser;


public interface ApiUserRepository extends JpaRepository<ApiUser, Long>{
	
	@Query("select u from ApiUser u where u.username=:username")
	ApiUser findByUsername(@Param("username") String username);

}
