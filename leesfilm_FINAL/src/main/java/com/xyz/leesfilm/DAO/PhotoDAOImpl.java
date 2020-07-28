package com.xyz.leesfilm.DAO;

import java.util.List;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xyz.leesfilm.DTO.PhotoDTO;

@Repository
public class PhotoDAOImpl implements PhotoDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.xyz.leesfilm.photoMapper";

	@Override
	public int insertPhoto(PhotoDTO photoDTO) {
		return sqlSession.insert(namespace + ".insertPhoto", photoDTO);
	}

	@Override
	public List<PhotoDTO> selectPhotoList() {
		return sqlSession.selectList(namespace + ".selectPhotoList");
	}

	@Override
	public int deletePhotoCategory(PhotoDTO photoDTO) {
		return sqlSession.delete(namespace + ".deletePhotoCategory", photoDTO);
	}
	
	@Override
	public int deletePhoto(int photo_id) {
		return sqlSession.delete(namespace + ".deletePhoto", photo_id);
	}

	@Override
	public int updatePhoto(PhotoDTO photoDTO) {
		return sqlSession.update(namespace + ".updatePhoto", photoDTO);
	}
	
	@Override
	public int getPhotoOrder(String category) {
		return sqlSession.update(namespace+".getPhotoOrder", category);
	}
	
	@Override
	public List<String> getPhotoCategory(int id) {
		return sqlSession.selectList(namespace+".getPhotoCategory", id);
	}

	@Override
	public int downPhotoOrder(int std) {
		return sqlSession.update(namespace+".downPhotoOrder", std);
	}
	
	@Override
	public int updatePhotoCategory(PhotoDTO photoDTO) {
		return sqlSession.update(namespace+".updatePhotoCategory", photoDTO);
	}

}