package com.gamebase.general.model.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamebase.general.model.OnlineUser;

@Repository
public interface OnlineUserRepo extends CrudRepository<OnlineUser, String> {

}
