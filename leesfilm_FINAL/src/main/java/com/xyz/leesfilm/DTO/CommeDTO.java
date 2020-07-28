package com.xyz.leesfilm.DTO;

import org.springframework.stereotype.Component;

@Component
public class CommeDTO {

	private int c_Id;
	private String c_Category;
	private String c_Name;
	private int c_cate_order;
	
	public int getC_cate_order() {
		return c_cate_order;
	}
	public void setC_cate_order(int c_cate_order) {
		this.c_cate_order = c_cate_order;
	}
	
	public int getC_Id() {
		return c_Id;
	}
	public void setC_Id(int c_Id) {
		this.c_Id = c_Id;
	}
	public String getC_Category() {
		return c_Category;
	}
	public void setC_Category(String c_Category) {
		this.c_Category = c_Category;
	}
	public String getC_Name() {
		return c_Name;
	}
	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}
	
	
}
