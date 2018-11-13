package mrcm.twitanalysis.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mrcm.twitanalysis.db.entity.ApiUser;
import mrcm.twitanalysis.db.entity.Twit;


public interface TwitRepository extends JpaRepository<Twit, Long> {
	
	@Query("select t from Twit t where t.apiUser=:apiUser")
	List<Twit> findAllByApiUser(@Param("apiUser") ApiUser apiUser);

}
