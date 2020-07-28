package com.xyz.leesfilm.DTO;

import org.springframework.stereotype.Component;

@Component
public class HomepicDTO {
	private int h_Id;
	private String h_name;

	public int getH_Id() {
		return h_Id;
	}

	public void setH_Id(int h_Id) {
		this.h_Id = h_Id;
	}

	public String getH_name() {
		return h_name;
	}

	public void setH_name(String h_name) {
		this.h_name = h_name;
	}

}