package com.makershark.ums.service;

import java.util.Map;

import com.makershark.ums.dto.UserDto;

public interface UserService {

	public Map<String, String> saveUser(UserDto userDto);
	public UserDto bringUserByUserName(String userName);
	public Boolean doesEmailExists(String email);
	public Boolean doesMobileNoExists(String mobileNo);
	public Boolean doesUserNameExists(String userName);
}
