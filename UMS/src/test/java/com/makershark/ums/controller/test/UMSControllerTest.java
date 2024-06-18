package com.makershark.ums.controller.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.makershark.ums.controller.UMSController;
import com.makershark.ums.dto.UserDto;
import com.makershark.ums.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UMSControllerTest {
	
	@Mock
	private UserService userService;
	@InjectMocks
	private UMSController umsController;

	@Test
	@DisplayName("testing register user method...")
	void testRegisterUser() {
		Map<String, String> responseMap = new HashMap<>();
		UserDto userDto = new UserDto();
		Mockito.when(userService.saveUser(Mockito.any(UserDto.class))).thenReturn(responseMap);
		ResponseEntity<Map<String, String>> response = umsController.registerUser(userDto);
		assertNotNull(response);
	}
	
	@Test
	@DisplayName("testing fetch user method...")
	void testFetchUser() {
		UserDto userDto = new UserDto();
		Mockito.when(userService.bringUserByUserName(Mockito.anyString())).thenReturn(userDto);
		ResponseEntity<UserDto> response = umsController.fetchUser("");
		assertNotNull(response);
	}
}
