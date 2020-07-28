package com.xyz.leesfilm.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xyz.leesfilm.DTO.HomepicDTO;

@Repository
public class HomepicDAO {
	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.xyz.leesfilm.photoMapper";

	public List<HomepicDTO> selectHomeList() {
		
		return sqlSession.selectList(namespace + ".selectHomeList");
	}

	public int deleteHome(int homepic_id) {
		return sqlSession.delete(namespace + ".deleteHome", homepic_id);
	}

	public int updateHome(HomepicDTO homepicDTO) {
		return sqlSession.update(namespace + ".updateHome", homepicDTO);
	}


}