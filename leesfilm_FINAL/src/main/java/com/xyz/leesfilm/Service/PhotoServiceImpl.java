package com.xyz.leesfilm.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.xyz.leesfilm.DAO.PhotoDAO;
import com.xyz.leesfilm.DTO.PhotoDTO;

@Service("PhotoService")
public class PhotoServiceImpl implements PhotoService {

   @Inject
   private PhotoDAO photoDAO;

   @Override
   public PhotoDTO insertPhoto(PhotoDTO photoDTO) {
       photoDAO.insertPhoto(photoDTO);
       return photoDTO;
   }

   @Override
   public List<PhotoDTO> selectPhotoList() {
      List<PhotoDTO> photolist = photoDAO.selectPhotoList();
      return photolist;
   }

   @Override
   public PhotoDTO deletePhotoCategory(PhotoDTO photoDTO) {
       photoDAO.deletePhotoCategory(photoDTO);
       return photoDTO;
   }

}