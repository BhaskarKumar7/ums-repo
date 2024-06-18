package com.makershark.ums.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.makershark.ums.dto.AddressDto;
import com.makershark.ums.dto.UserDto;
import com.makershark.ums.entity.AddressEntity;
import com.makershark.ums.entity.UserEntity;
import com.makershark.ums.enums.GenderEnum;
import com.makershark.ums.exception.UserCreationFailureException;
import com.makershark.ums.exception.UserNotFoundException;
import com.makershark.ums.repository.UserRepository;
import com.makershark.ums.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Value("${password.salt}")
	private String salt;

	@Override
	public Map<String, String> saveUser(UserDto userDto) {
		// ------------------ arraging the values from dto object to entity object
		// ---------------------------
		log.info("=== begin UserServiceImpl.saveUser ====");
		UserEntity userEntity = new UserEntity();
		Map<String, String> entityMap = new HashMap<>();
		try {
			log.info("*** setting user details ***");
			userEntity.setFirstName(userDto.getFirstName());
			userEntity.setLastName(userDto.getLastName());
			userEntity.setUserName(userDto.getUserName());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setMobileNo(userDto.getMobileNo());
			userEntity.setGender(GenderEnum.valueOf(userDto.getGender().toUpperCase()));
			userEntity.setDateOfBirth(userDto.getDateOfBirth());
			userEntity.setPassword(BCrypt.hashpw(userDto.getPassword(), salt));
			userEntity.setCreatedTime(LocalDateTime.now());
			userEntity.setModifiedTime(LocalDateTime.now());
			/*-------------------------- individually setting values to address entity object 
			since it is nested entity of user entity*/
			log.info("*** setting address details ***");
			AddressEntity addressEntity = new AddressEntity();
			AddressDto addressDto = userDto.getAddress();
			addressEntity.setAddressLine(addressDto.getAddressLine());
			addressEntity.setCountry(addressDto.getCountry());
			addressEntity.setState(addressDto.getState());
			addressEntity.setCity(addressDto.getCity());
			addressEntity.setPincode(addressDto.getPincode());
			addressEntity.setDoorNo(addressDto.getDoorNo());

			userEntity.setAddress(addressEntity);
			log.info("*** saving user ***");
			userEntity = userRepo.save(userEntity);
		} catch (Exception e) {
			log.error(">>>>>>>>>>>>>>exception occured while saving user",e);
			throw new UserCreationFailureException("Problem occured during user registration,Please try again.");
		}
		log.info("** user saved successfullly ***");
		entityMap.put("user id", userEntity.getUserId().toString());
		entityMap.put("message", "User registration successfull");
		log.info("=== end UserServiceImpl.saveUser ===");
		return entityMap;
	}

	@Override
	public UserDto bringUserByUserName(String userName) {
		log.info("=== begin UserServiceImpl.bringUserByUserName ===");
		UserEntity userEntity = userRepo.findByUserName(userName);
		if(userEntity == null)	{
			throw new UserNotFoundException("user not found with username: " + userName);
		}
		log.info("*** fetched user from database ***");
		AddressEntity addressEntity = userEntity.getAddress();
		UserDto userDto = new UserDto();
		AddressDto addressDto = new AddressDto();
		log.info("*** populating user details ***");
		userDto.setFirstName(userEntity.getFirstName());
		userDto.setLastName(userEntity.getLastName());
		userDto.setUserName(userEntity.getUserName());
		userDto.setUserId(userEntity.getUserId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setMobileNo(userEntity.getMobileNo());
		userDto.setGender(userEntity.getGender().toString());
		userDto.setDateOfBirth(userEntity.getDateOfBirth());
		log.info("*** populating address details ***");
		addressDto.setAddressLine(addressEntity.getAddressLine());
		addressDto.setCity(addressEntity.getCity());
		addressDto.setCountry(addressEntity.getCountry());
		addressDto.setState(addressEntity.getState());
		addressDto.setDoorNo(addressEntity.getDoorNo());
		addressDto.setPincode(addressEntity.getPincode());

		userDto.setAddress(addressDto);
		log.info("=== end UserServiceImpl.bringUserByUserName ===");
		return userDto;
	}

	@Override
	public Boolean doesEmailExists(String email) {
		return userRepo.findByEmail(email) == null ? false : true;
	}

	@Override
	public Boolean doesMobileNoExists(String mobileNo) {
		return userRepo.findByMobileNo(mobileNo) == null ? false : true;
	}

	@Override
	public Boolean doesUserNameExists(String userName) {
		return userRepo.findByUserName(userName) == null ? false : true;
	}

}
