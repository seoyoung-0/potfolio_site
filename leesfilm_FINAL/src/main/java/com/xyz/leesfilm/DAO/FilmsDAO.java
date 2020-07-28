package com.xyz.leesfilm.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.xyz.leesfilm.DTO.FilmsDTO;

@Repository
public class FilmsDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.xyz.leesfilm.photoMapper";

	public int insertFilms(FilmsDTO filmsDTO) {
		return sqlSession.insert(namespace + ".insertFilms", filmsDTO);
	}

	public List<FilmsDTO> selectFilmsList() {
		return sqlSession.selectList(namespace + ".selectFilmsList");
	}

	public int deleteFilms(int film_id) {
		return sqlSession.delete(namespace + ".deleteFilms", film_id);
	}

	public int updateFilms(FilmsDTO filmsDTO) {
		return sqlSession.update(namespace + ".updateFilms", filmsDTO);
	}
}
