package com.xyz.leesfilm.Service;

import java.util.List;
import com.xyz.leesfilm.DTO.PhotoDTO;


public interface PhotoService {
   public PhotoDTO insertPhoto(PhotoDTO photoDTO);
   public List<PhotoDTO> selectPhotoList();
   public PhotoDTO deletePhotoCategory(PhotoDTO photoDTO);
}