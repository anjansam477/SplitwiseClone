package net.splitwise.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.splitwise.springboot.exception.ResoruceNotFoundException;
import net.splitwise.springboot.model.Friend;
import net.splitwise.springboot.repository.FriendRepository;

@RestController
@RequestMapping("/apli/v1/")
public class FriendController {
	
	@Autowired
	private FriendRepository friendRepository;
	
	//get friends
	@GetMapping("friends")
	public List<Friend> getAllFriends(){
		return this.friendRepository.findAll();
	}
	
	//get friend by id
	@GetMapping("/friends/{id}")
	public ResponseEntity<Friend> getAllFriendsById(@PathVariable(value = "id")Long friendId) throws ResoruceNotFoundException{
		Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new ResoruceNotFoundException("Friend not found for this id ::" + friendId));
		return ResponseEntity.ok().body(friend);
	}
	
	//save friend
	@PostMapping("friends")
	public Friend createFriend(@RequestBody Friend friend) {
		return this.friendRepository.save(friend);
	}
	
	//update friend
	@PutMapping("friends/{id}")
	public ResponseEntity<Friend> updateFriend(@PathVariable(value = "id") Long friendId, @Validated @RequestBody Friend friendDetails) throws ResoruceNotFoundException{
		Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new ResoruceNotFoundException("Friend not found for this id ::" + friendId));
		friend.setEmail(friendDetails.getEmail());
		friend.setName(friendDetails.getName());
		friend.setPhone(friendDetails.getPhone());
		friend.setImg(friendDetails.getImg());
		return ResponseEntity.ok(this.friendRepository.save(friend));
	}
	
	//delete friend
	@DeleteMapping("friends/{id}/")
	public Map<String, Boolean> deleteFriend(@PathVariable(value = "id") Long friendId) throws ResoruceNotFoundException{
		Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new ResoruceNotFoundException("Friend not found for this id ::" + friendId));
		this.friendRepository.delete(friend);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}
	
}