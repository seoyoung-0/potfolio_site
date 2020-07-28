package com.xyz.leesfilm.DTO;

import org.springframework.stereotype.Component;

@Component
public class PhotoDTO {
	private int p_Id;
	private String p_Category;
	private String p_Name;
	private int p_cate_order;

	public int getP_Id() {
		return p_Id;
	}
	public void setP_Id(int p_Id) {
		this.p_Id = p_Id;
	}
	public String getP_Category() {
		return p_Category;
	}
	public void setP_Category(String p_Category) {
		this.p_Category = p_Category;
	}
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
	public int getP_cate_order() {
		return p_cate_order;
	}
	public void setP_cate_order(int p_cate_order) {
		this.p_cate_order = p_cate_order;
	}
}
