package com.xyz.leesfilm.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xyz.leesfilm.DTO.CommeDTO;

@Repository
public class CommeDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.xyz.leesfilm.photoMapper";

	public int insertComme(CommeDTO commeDTO) {
		return sqlSession.insert(namespace + ".insertComme", commeDTO);
	}

	public List<CommeDTO> selectCommeList() {
		return sqlSession.selectList(namespace + ".selectCommeList");
	}

	public int deleteCommeCategory(CommeDTO commeDTO) {
		return sqlSession.delete(namespace + ".deleteCommeCategory", commeDTO);
	}

	public int deleteComme(int comme_id) {
		return sqlSession.delete(namespace + ".deleteComme", comme_id);
	}

	public int updateComme(CommeDTO commeDTO) {
		return sqlSession.update(namespace + ".updateComme", commeDTO);
	}
	
	public int downCommeOrder(int std) {
		return sqlSession.update(namespace+".downCommeOrder", std);
	}
	
	public List<String> getCommeCategory(int id) {
		return sqlSession.selectList(namespace+".getCommeCategory", id);
	}

	public int updateCommeCategory(CommeDTO commeDTO) {
		return sqlSession.update(namespace + ".updateCommeCategory", commeDTO);
	}
}