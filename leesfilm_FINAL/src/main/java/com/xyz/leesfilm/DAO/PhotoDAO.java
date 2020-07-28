package com.xyz.leesfilm.DAO;

import java.util.List;

import com.xyz.leesfilm.DTO.PhotoDTO;

public interface PhotoDAO {
	public int insertPhoto(PhotoDTO photoDTO);
	public List<PhotoDTO> selectPhotoList();
	public int deletePhotoCategory(PhotoDTO photoDTO);
	public int deletePhoto(int photo_id);
	public int updatePhoto(PhotoDTO photoDTO);
	public int getPhotoOrder(String category);
	public List<String> getPhotoCategory(int id);
	public int downPhotoOrder(int std);
	public int updatePhotoCategory(PhotoDTO photoDTO);
}