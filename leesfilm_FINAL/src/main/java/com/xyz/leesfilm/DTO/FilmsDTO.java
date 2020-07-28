package com.xyz.leesfilm.DTO;

import org.springframework.stereotype.Component;

@Component
public class FilmsDTO {
	private int f_Id;
	private String f_Category;
	private String f_Name;
	
	public int getF_Id() {
		return f_Id;
	}
	public void setF_Id(int f_Id) {
		this.f_Id = f_Id;
	}
	public String getF_Category() {
		return f_Category;
	}
	public void setF_Category(String f_Category) {
		this.f_Category = f_Category;
	}
	public String getF_Name() {
		return f_Name;
	}
	public void setF_Name(String f_Name) {
		this.f_Name = f_Name;
	}
	
	
}
