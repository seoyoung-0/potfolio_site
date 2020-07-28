package com.xyz.leesfilm.DTO;

import org.springframework.stereotype.Component;

@Component
public class CategoryDTO {

	private String c_Category;
	private String p_Category;
	
	private int p_cate_order;
	private int c_cate_order;
	
	private String cate_name;
	private int cate_num;
	private int cate_order;
	private String cate_type;
	
	public int getP_cate_order() {
		return p_cate_order;
	}

	public String getCate_name() {
		return cate_name;
	}

	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}

	public int getCate_num() {
		return cate_num;
	}

	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}

	public int getCate_order() {
		return cate_order;
	}

	public void setCate_order(int cate_order) {
		this.cate_order = cate_order;
	}

	public String getCate_type() {
		return cate_type;
	}

	public void setCate_type(String cate_type) {
		this.cate_type = cate_type;
	}

	public void setP_cate_order(int p_cate_order) {
		this.p_cate_order = p_cate_order;
	}

	public int getC_cate_order() {
		return c_cate_order;
	}

	public void setC_cate_order(int c_cate_order) {
		this.c_cate_order = c_cate_order;
	}
	
	public String getC_Category() { return c_Category; }
	
	public String getP_Category() { return p_Category; }
	
}
