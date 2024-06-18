package com.makershark.ums.entity;

import java.time.LocalDateTime;

import com.makershark.ums.constant.AppConstants;
import com.makershark.ums.enums.GenderEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.UMS_SCHEMA)
@Getter
@Setter
public class UserEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.UMS_SCHEMA, initialValue = 1, allocationSize = 1, 
	sequenceName = "seq_user", name = "user_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
	private Long userId;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private String mobileNo;
	@Enumerated(EnumType.STRING)
	private GenderEnum gender;
	private String dateOfBirth;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private AddressEntity address;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
}
