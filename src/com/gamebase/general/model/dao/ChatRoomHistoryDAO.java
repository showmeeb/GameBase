package com.gamebase.general.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.ChatRoomHistory;

@Repository
public interface ChatRoomHistoryDAO extends CrudRepository<ChatRoomHistory, String> {


}
