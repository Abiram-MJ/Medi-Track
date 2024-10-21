package com.example.MediTrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.MediTrack.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u where u.username = :username")
	//@Query(value="select * from Users  where username = ?1",nativeQuery = true)
	public User getUserByUsername(@Param("username") String username);
	
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);
}