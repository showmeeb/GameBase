package com.gamebase.general.model.dao;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.ChatRoom;

@Repository
public interface ChatRoomDAO extends CrudRepository<ChatRoom, Integer> {

	List<ChatRoom> findBySenderOrReceiver(Integer sender, Integer receiver);

}
