package com.gamebase.member.model.service;

import com.gamebase.member.model.Role;

public interface IRoleService {
	public Role getRoleByUserId(Integer usreId);
	public void changeRole(Role role);
}
