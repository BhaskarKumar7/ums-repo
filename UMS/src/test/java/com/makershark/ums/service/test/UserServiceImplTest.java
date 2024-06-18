package com.makershark.ums.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.makershark.ums.dto.UserDto;
import com.makershark.ums.entity.AddressEntity;
import com.makershark.ums.entity.UserEntity;
import com.makershark.ums.enums.GenderEnum;
import com.makershark.ums.exception.UserCreationFailureException;
import com.makershark.ums.exception.UserNotFoundException;
import com.makershark.ums.repository.UserRepository;
import com.makershark.ums.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepo;
	@InjectMocks
	private UserServiceImpl userServiceImpl;
	
	@Test
	@DisplayName("testing saveUser method...")
	void testSaveUser() throws Exception {
		ReflectionTestUtils.setField(userServiceImpl, "salt", "$2a$10$L3PTdkN6VaA6xekKQ6wv5O");
		ObjectMapper mapper = new ObjectMapper();
		UserEntity mockUserEntity = new UserEntity();
		mockUserEntity.setUserId(1l);
		UserDto userDto = mapper.readValue(new ClassPathResource
				("mock-json/saveUserRequest.json").getInputStream(), UserDto.class);
		Mockito.when(userRepo.save(Mockito.any())).thenReturn(mockUserEntity);
		Map<String, String> responseMap = userServiceImpl.saveUser(userDto);
		assertNotNull(responseMap);
		
		Mockito.when(userRepo.save(Mockito.any())).thenThrow(RuntimeException.class);
		assertThrows(UserCreationFailureException.class, () -> userServiceImpl.saveUser(userDto));
	}
	
	@Test
	@DisplayName("testing bringUserByUserName method...")
	void testBringUserByUserName() {
		UserEntity mockUserEntity = new UserEntity();
		mockUserEntity.setGender(GenderEnum.MALE);
		AddressEntity mockAddressEntity = new AddressEntity();
		mockUserEntity.setAddress(mockAddressEntity);
		Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(mockUserEntity);
		UserDto userDto = userServiceImpl.bringUserByUserName("abc");
		assertNotNull(userDto);
		
		Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(null);
		assertThrows(UserNotFoundException.class, () -> userServiceImpl.bringUserByUserName("abc"));
		
	}
	
	@Test
	@DisplayName("testing doesEmailExists method...")
	void testDoesEmailExists() {
		UserEntity userEntity = new UserEntity();
		Mockito.when(userRepo.findByEmail(Mockito.anyString())).thenReturn(userEntity);
		boolean response = userServiceImpl.doesEmailExists("abd");
		assertNotNull(response);
		
		Mockito.when(userRepo.findByEmail(Mockito.anyString())).thenReturn(null);
		boolean response2 = userServiceImpl.doesEmailExists("abd");
		assertNotNull(response2);
	}
	
	@Test
	@DisplayName("testing doesMobileNoExists method...")
	void testdoesMobileNoExists() {
		UserEntity mockUserEntity = new UserEntity();
		Mockito.when(userRepo.findByMobileNo(Mockito.anyString())).thenReturn(mockUserEntity);
		boolean resp = userServiceImpl.doesMobileNoExists("98989");
		assertNotNull(resp);
		
		Mockito.when(userRepo.findByMobileNo(Mockito.anyString())).thenReturn(null);
		boolean resp2 = userServiceImpl.doesMobileNoExists("9898");
		assertNotNull(resp2);
	}
	
	@Test
	@DisplayName("testing doesUserNameExists method...")
	void testDoesUserNameExists() {
		UserEntity mockUserEntity = new UserEntity();
		Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(mockUserEntity);
		boolean resp = userServiceImpl.doesUserNameExists("ccc");
		assertNotNull(resp);
		
		Mockito.when(userRepo.findByUserName(Mockito.anyString())).thenReturn(null);
		boolean resp2 = userServiceImpl.doesUserNameExists("ccc");
		assertNotNull(resp2);
	}
}
