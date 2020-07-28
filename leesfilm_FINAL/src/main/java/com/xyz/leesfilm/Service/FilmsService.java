package com.xyz.leesfilm.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.xyz.leesfilm.DAO.FilmsDAO;
import com.xyz.leesfilm.DTO.FilmsDTO;

@Service("FilmsService")
public class FilmsService {
	@Inject
	private FilmsDAO filmsDAO;

	public FilmsDTO insertPhoto(FilmsDTO filmsDTO) {
		filmsDAO.insertFilms(filmsDTO);
		 return filmsDTO;
	}


	public List<FilmsDTO> selectFilmsList() {
		List<FilmsDTO> filmslist = filmsDAO.selectFilmsList();
		return filmslist;
	}
}