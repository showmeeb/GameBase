package com.gamebase.member.model.dao;

import com.gamebase.member.model.Role;

public interface IRoleDAO {
	public Role getRoleByUserId(Integer usreId);
	public void changeRole(Role role);
}
