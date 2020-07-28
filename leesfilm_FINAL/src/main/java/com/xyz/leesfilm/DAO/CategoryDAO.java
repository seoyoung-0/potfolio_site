package com.xyz.leesfilm.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xyz.leesfilm.DTO.CategoryDTO;

@Repository
public class CategoryDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.xyz.leesfilm.photoMapper";
	
	public List<CategoryDTO> selectCategoryList() {
		return sqlSession.selectList(namespace+".selectCategoryList");
	}
	
	public List<Integer> count(String type) {
		return sqlSession.selectList(namespace+".count", type);
	}
	
	public int insertCategory(CategoryDTO categoryDTO) {
	      return sqlSession.insert(namespace+".insertCategory", categoryDTO);
	   }
	
	public List<Integer> getOrder(String name) {
		return sqlSession.selectList(namespace+".getOrder", name);
	}
	
	 public int deleteCategory(CategoryDTO categoryDTO) {
			return sqlSession.delete(namespace+".deleteCategory",categoryDTO);
		}
	 
	 public List<Integer> getCateNum(String name) {
		 return sqlSession.selectList(namespace+".getCateNum", name);
	 }
	 
	 public int updateCateNum(CategoryDTO categoryDTO) {
		 return sqlSession.update(namespace+".updateCateNum", categoryDTO);
	 }
	 
	 public int downCateOrder(CategoryDTO categoryDTO) {
		 return sqlSession.update(namespace+".downCateOrder", categoryDTO);
	 }
	 public int updateCateOrder(CategoryDTO categoryDTO) {
		 return sqlSession.update(namespace+".updateCateOrder", categoryDTO);
	 }
}





	
