package com.makershark.ums.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.makershark.ums.dto.UserDto;
import com.makershark.ums.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Validated
@RequestMapping("api/user")
@AllArgsConstructor
public class UMSController {

	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserDto userDto) {
		log.info("--- begin UMSController.registerUser ----");
		Map<String, String> responseMap = userService.saveUser(userDto);
		log.info("--- end UMSController.registerUser ----");
		return new ResponseEntity<>(responseMap,HttpStatus.OK);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<UserDto> fetchUser(@RequestParam String userName) {
		log.info("--- begin UMSController.fetchUser ----");
		UserDto userDto = userService.bringUserByUserName(userName);
		log.info("--- end UMSController.fetchUser ----");
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
}
