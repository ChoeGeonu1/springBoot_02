package com.example.spring.myapp.dto;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class CommonProperties {
	
	@Value("${common.crtfc_key}")
	private String crtfc_key;


}
