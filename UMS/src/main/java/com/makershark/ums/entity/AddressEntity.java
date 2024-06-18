package com.makershark.ums.entity;

import com.makershark.ums.constant.AppConstants;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(schema = AppConstants.UMS_SCHEMA)
@Getter
@Setter
public class AddressEntity {

	@Id
	@SequenceGenerator(schema = AppConstants.UMS_SCHEMA,initialValue = 1,allocationSize = 1,
	sequenceName = "seq_address",name = "address_seq_generator")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "address_seq_generator")
	private Long addressId;
	private String addressLine;
	private String country;
	private String state;
	private String city;
	private String pincode;
	private String doorNo;
}
