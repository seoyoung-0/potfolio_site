package com.xyz.leesfilm.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.xyz.leesfilm.DAO.CommeDAO;
import com.xyz.leesfilm.DTO.CommeDTO;

@Service("CommeService")
public class CommeService {
   @Inject
   private CommeDAO commeDAO;
   
   public CommeDTO insertComme(CommeDTO commeDTO) {
      commeDAO.insertComme(commeDTO);
      return commeDTO;
   }
   
   public List<CommeDTO> selectCommeList() {
      List<CommeDTO> commerciallist = commeDAO.selectCommeList();
      return commerciallist;
   }
   
   public CommeDTO deleteCommeCategory(CommeDTO commeDTO) {
      commeDAO.deleteCommeCategory(commeDTO);
      return commeDTO;
   }
}