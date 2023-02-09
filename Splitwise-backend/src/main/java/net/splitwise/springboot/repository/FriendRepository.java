package net.splitwise.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.splitwise.springboot.model.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long>{
	
	
	
}
