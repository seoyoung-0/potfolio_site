package com.xyz.leesfilm.Controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xyz.leesfilm.DAO.CategoryDAO;
import com.xyz.leesfilm.DAO.CommeDAO;
import com.xyz.leesfilm.DAO.PhotoDAO;
import com.xyz.leesfilm.DTO.CategoryDTO;
import com.xyz.leesfilm.DTO.CommeDTO;
import com.xyz.leesfilm.DTO.PhotoDTO;

@Controller
public class PhotoController {
	private static final Logger logger=LoggerFactory.getLogger(PhotoController.class);
	
	List<String> resultList;
	
	@Inject
	private PhotoDAO photoDAO;
	
	@Inject
	private CommeDAO commeDAO;
	
	@Inject
	private CategoryDAO categoryDAO;
	
	
	@RequestMapping(value="/uploadphoto",method={RequestMethod.GET,RequestMethod.POST})
	   public String uploadPhoto(Model model,
	         @RequestParam("photofile") String photoUrl,
	         @RequestParam("gugunSelect") String category,
	         HttpServletRequest request) {
	      PhotoDTO photoDTO = new PhotoDTO();
	      String url = photoUrl;
	      logger.info(category);
	      
	      String urlarr[] = url.split("/");
	      String photo_name = urlarr[5];
	      photoDTO.setP_Name(photo_name);
	      if(category.equals("¼ÒºÐ·ù Ãß°¡")) {
	         String addCate = request.getParameter("addCategory");
	         int count = categoryDAO.count("photo").get(0);
	         photoDTO.setP_Category(addCate);
	         photoDTO.setP_cate_order(count);
	         CategoryDTO categoryDTO = new CategoryDTO();
	         categoryDTO.setCate_name(addCate);
	         categoryDTO.setCate_num(1);
	         categoryDTO.setCate_order(count);
	         categoryDTO.setCate_type("photo");
	         categoryDAO.insertCategory(categoryDTO);
	      }else {
	         photoDTO.setP_Category(category);
	         int order = categoryDAO.getOrder(category).get(0);
	         photoDTO.setP_cate_order(order);
	         CategoryDTO categoryDTO = new CategoryDTO();
	         categoryDTO.setCate_name(category);
	         int num = categoryDAO.getCateNum(category).get(0);
	         categoryDTO.setCate_num(num+1);
	         categoryDTO.setCate_type("photo");
	         categoryDAO.updateCateNum(categoryDTO);
	      }
	      photoDAO.insertPhoto(photoDTO);
	      return "redirect:/photoselect";
	   }
	
	@RequestMapping(value= {"/photoselect","/photo"}, method={RequestMethod.GET,RequestMethod.POST})
	public String photo(Model model) {
		
		resultList= new ArrayList<String>();
		
		List<PhotoDTO> photoList = photoDAO.selectPhotoList();
		List<CommeDTO> commeList = commeDAO.selectCommeList();
		String[] photo_order = new String[categoryDAO.count("photo").get(0)];
		String[] comme_order = new String[categoryDAO.count("commercial").get(0)];
		for(int i=0;i<photoList.size();i++) {
			photo_order[photoList.get(i).getP_cate_order()] = photoList.get(i).getP_Category();
		}
		
		for(int i=0;i<commeList.size();i++) {
			comme_order[commeList.get(i).getC_cate_order()] = commeList.get(i).getC_Category();
		}
		
		LinkedHashMap<String, String> photomap = new LinkedHashMap<String, String>();
		for(int i=0;i<photoList.size();i++) {
			photomap.put(Integer.toString(photoList.get(i).getP_Id()), photoList.get(i).getP_Name());
			/*if(photomap.containsValue(photoList.get(i).getP_Name())) {
				continue;
			}
			else {	
				photomap.put(Integer.toString(photoList.get(i).getP_Id()), photoList.get(i).getP_Name());
			}*/
		}
	
		model.addAttribute("resultMap",photomap);
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		
		return "/photo";
	}
	
	@RequestMapping(value= {"/photo/{subvar}"}, method={RequestMethod.GET,RequestMethod.POST})
	public String subPhoto(@PathVariable String subvar, Model model) {
		
		resultList= new ArrayList<String>();
		
		List<PhotoDTO> photoList = photoDAO.selectPhotoList();
		List<CommeDTO> commeList = commeDAO.selectCommeList();
		
		String[] photo_order = new String[categoryDAO.count("photo").get(0)];
		String[] comme_order = new String[categoryDAO.count("commercial").get(0)];
		for(int i=0;i<photoList.size();i++) {
			photo_order[photoList.get(i).getP_cate_order()] = photoList.get(i).getP_Category();
		}
		
		for(int i=0;i<commeList.size();i++) {
			comme_order[commeList.get(i).getC_cate_order()] = commeList.get(i).getC_Category();
		}
		
		LinkedHashMap<String, String> photomap = new LinkedHashMap<String, String>();
		for(int i=0;i<photoList.size();i++) {
			if(photomap.containsValue(photoList.get(i).getP_Name())) {
				continue;
			}
			else if(photoList.get(i).getP_Category().equals(subvar)) {
				photomap.put(Integer.toString(photoList.get(i).getP_Id()), photoList.get(i).getP_Name());
			}
		}
	
		model.addAttribute("resultMap",photomap);
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		return "/photo";
	}
	

	@RequestMapping(value="/deletephoto", method={RequestMethod.GET,RequestMethod.POST})
	public String deletePhoto(Model model, @RequestParam("photo_id")int photo_id) {
		String category = photoDAO.getPhotoCategory(photo_id).get(0);
		CategoryDTO categoryDTO = new CategoryDTO();
	      categoryDTO.setCate_name(category);
	      categoryDTO.setCate_type("photo");
		if(categoryDAO.getCateNum(category).get(0)==1) {
			//Ä«ï¿½×°ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			int std = categoryDAO.getOrder(category).get(0);

		      categoryDTO.setCate_order(std);
		      categoryDAO.deleteCategory(categoryDTO);
		      
		      photoDAO.downPhotoOrder(std);
		 
		      categoryDAO.downCateOrder(categoryDTO);
		}
		else {
			int num = categoryDAO.getCateNum(category).get(0);
			categoryDTO.setCate_num(num-1);
			categoryDAO.updateCateNum(categoryDTO);
		}
		
		photoDAO.deletePhoto(photo_id);
		return "redirect:/photo";
	}
	
	@RequestMapping(value="/deletephotocategory",method={RequestMethod.POST})
	   public String deletePhotoCategory(Model model,
	         @RequestParam("gugunSelect") String category) {
	      PhotoDTO photoDTO = new PhotoDTO();
	      logger.info(category);
	      int std = categoryDAO.getOrder(category).get(0);

	      CategoryDTO categoryDTO = new CategoryDTO();
	      categoryDTO.setCate_name(category);
	      categoryDTO.setCate_type("photo");
	      categoryDTO.setCate_order(std);
	      categoryDAO.deleteCategory(categoryDTO);
	      
	      photoDTO.setP_Category(category);
	      
	      photoDAO.deletePhotoCategory(photoDTO);
	      photoDAO.downPhotoOrder(std);
	 	 
	      categoryDAO.downCateOrder(categoryDTO);
	      return "forward:/photoselect";
	   }
	
	@RequestMapping(value="/updatephoto", method={RequestMethod.GET,RequestMethod.POST})
	public String updatePhoto(Model model, 
			@RequestParam("photo_id")int photo_id,
			@RequestParam("photofile")String photo_real_name) {
		String urlarr[] = photo_real_name.split("/");
		String photo_name = urlarr[5];

		PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setP_Id(photo_id);
		photoDTO.setP_Name(photo_name);
		photoDAO.updatePhoto(photoDTO);

		return "redirect:/photo";
	}
}
