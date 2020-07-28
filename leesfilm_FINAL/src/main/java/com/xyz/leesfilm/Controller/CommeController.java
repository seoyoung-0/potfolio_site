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
public class CommeController {
	private static final Logger logger=LoggerFactory.getLogger(CommeController.class);
	
	@Inject
	private CommeDAO commeDAO;
	
	@Inject
	private PhotoDAO photoDAO;
	
	@Inject
	private CategoryDAO categoryDAO;

	List<String> resultList;
	
	@RequestMapping(value="/uploadCommercial",method={RequestMethod.GET,RequestMethod.POST})
	   public String uploadVideo(Model model,
	         @RequestParam("video_url") String videourl,
	         @RequestParam("gugunSelect") String category,
	         HttpServletRequest request) {
	         
	      CommeDTO commeDTO = new CommeDTO();
	      String video_id=videourl.substring(videourl.lastIndexOf("=")+1);
	      
	      commeDTO.setC_Name(video_id);
	      if(category.equals("¼ÒºÐ·ù Ãß°¡")) {
	         String addCate = request.getParameter("addCategory");
	         int count = categoryDAO.count("commercial").get(0);
	         commeDTO.setC_Category(addCate);
	         commeDTO.setC_cate_order(count);
	         CategoryDTO categoryDTO = new CategoryDTO();
	         categoryDTO.setCate_name(addCate);
	         categoryDTO.setCate_num(1);
	         categoryDTO.setCate_order(count);
	         categoryDTO.setCate_type("commercial");
	         categoryDAO.insertCategory(categoryDTO);
	      }else {
	         commeDTO.setC_Category(category);
	         int order = categoryDAO.getOrder(category).get(0);
	         commeDTO.setC_cate_order(order);
	         CategoryDTO categoryDTO = new CategoryDTO();
	         categoryDTO.setCate_name(category);
	         int num = categoryDAO.getCateNum(category).get(0);
	         categoryDTO.setCate_num(num+1);
	         categoryDTO.setCate_type("commercial");
	         categoryDAO.updateCateNum(categoryDTO);
	      }
	      commeDAO.insertComme(commeDTO);
	      return "redirect:/commeselect";
	   }
	
	@RequestMapping(value= {"/commeselect","/commercial"}, method={RequestMethod.GET,RequestMethod.POST})
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
		
		LinkedHashMap<String, String> commemap = new LinkedHashMap<String, String>();
		for(int i=0;i<commeList.size();i++) {
			commemap.put(Integer.toString(commeList.get(i).getC_Id()), commeList.get(i).getC_Name());
		}
		
		model.addAttribute("resultCommeMap",commemap);
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		
		return "/commercial";		
	}
	
	   @RequestMapping(value= {"/commercial/{subvar}"}, method={RequestMethod.GET,RequestMethod.POST})
	   public String photo(@PathVariable String subvar, Model model) {
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
			
	     
	      LinkedHashMap<String, String> commemap = new LinkedHashMap<String, String>();
	      for(int i=0;i<commeList.size();i++) {
	    	  if(commeList.get(i).getC_Category().equals(subvar)) {
	            commemap.put(Integer.toString(commeList.get(i).getC_Id()), commeList.get(i).getC_Name());
	         }
	      }
	      
	      model.addAttribute("resultCommeMap",commemap);
	      model.addAttribute("comCategory", comme_order);
			model.addAttribute("photoCategory", photo_order);
	      return "/commercial";
	      
	   }

	
	@RequestMapping(value="/deletecommecategory",method={RequestMethod.POST})
	   public String deleteCommeCategory(Model model,
	         @RequestParam("gugunSelect") String category) {
	      CommeDTO commeDTO = new CommeDTO();
	      logger.info(category);
	      int std = categoryDAO.getOrder(category).get(0);

	      CategoryDTO categoryDTO = new CategoryDTO();
	      categoryDTO.setCate_name(category);
	      categoryDTO.setCate_type("commercial");
	      categoryDTO.setCate_order(std);
	      categoryDAO.deleteCategory(categoryDTO);
	      
	      commeDTO.setC_Category(category);
	      
	      commeDAO.deleteCommeCategory(commeDTO);
commeDAO.downCommeOrder(std);
	      
	      categoryDAO.downCateOrder(categoryDTO);
	      return "forward:/commeselect";
	   }
	
	@RequestMapping(value="/deletecomme", method={RequestMethod.GET,RequestMethod.POST})
	public String deleteComme(Model model, @RequestParam("comme_id")int comme_id) {
		String category = commeDAO.getCommeCategory(comme_id).get(0);
		CategoryDTO categoryDTO = new CategoryDTO();
	      categoryDTO.setCate_name(category);
	      categoryDTO.setCate_type("commercial");
		if(categoryDAO.getCateNum(category).get(0)==1) {
			//Ä«ï¿½×°ï¿½ ï¿½ï¿½Ã¼ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
			int std = categoryDAO.getOrder(category).get(0);

		      
		      categoryDTO.setCate_order(std);
		      categoryDAO.deleteCategory(categoryDTO);
		      
		      commeDAO.downCommeOrder(std);
		 
		      categoryDAO.downCateOrder(categoryDTO);
		}
		else {
			int num = categoryDAO.getCateNum(category).get(0);
			categoryDTO.setCate_num(num-1);
			categoryDAO.updateCateNum(categoryDTO);
		}
		
		commeDAO.deleteComme(comme_id);
		return "redirect:/commercial";
	}

	@RequestMapping(value="/updatecomme", method={RequestMethod.GET,RequestMethod.POST})
	public String updateFilm(Model model, 
			@RequestParam("comme_id")int comme_id,
			@RequestParam("video_comme_url")String comme_real_name) {
		//System.out.println("real name:"+ comme_real_name);
		String comme_name=comme_real_name.substring(comme_real_name.lastIndexOf("=")+1);
		//System.out.println("film_name"+comme_name);
		CommeDTO commeDTO = new CommeDTO();
		commeDTO.setC_Id(comme_id);
		commeDTO.setC_Name(comme_name);
		commeDAO.updateComme(commeDTO);
		return "redirect:/commercial";
	}

}
