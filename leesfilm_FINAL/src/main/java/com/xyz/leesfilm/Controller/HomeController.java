package com.xyz.leesfilm.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xyz.leesfilm.DAO.CategoryDAO;
import com.xyz.leesfilm.DAO.CommeDAO;
import com.xyz.leesfilm.DAO.HomepicDAO;
import com.xyz.leesfilm.DAO.PhotoDAO;
import com.xyz.leesfilm.DTO.CategoryDTO;
import com.xyz.leesfilm.DTO.CommeDTO;
import com.xyz.leesfilm.DTO.HomepicDTO;
//import com.xyz.leesfilm.DTO.HomepicDTO;
import com.xyz.leesfilm.DTO.PhotoDTO;

@Controller
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Inject
	private CategoryDAO categoryDAO;
	@Inject
	private PhotoDAO photoDAO;
	@Inject
	private CommeDAO commeDAO;
	@Inject
	private HomepicDAO homepicDAO;
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale {}.", locale);
		List<PhotoDTO> photoList = photoDAO.selectPhotoList();
		List<CommeDTO> commeList = commeDAO.selectCommeList();
		List<HomepicDTO> homepicList = homepicDAO.selectHomeList();
		
		LinkedHashMap<String, String> homepicMap = new LinkedHashMap<String, String>();
		Map<String, String> hompicMain = new HashMap<String, String>();
		String[] photo_order = new String[categoryDAO.count("photo").get(0)];
		String[] comme_order = new String[categoryDAO.count("commercial").get(0)];
		
		for(int i=0;i<photoList.size();i++) {
			photo_order[photoList.get(i).getP_cate_order()] = photoList.get(i).getP_Category();
		}
		
		for(int i=0;i<commeList.size();i++) {
			comme_order[commeList.get(i).getC_cate_order()] = commeList.get(i).getC_Category();
		}
		
		for (int i=0;i<homepicList.size();i++) {
			if(homepicList.get(i).getH_Id()==1) {
				hompicMain.put(Integer.toString(homepicList.get(i).getH_Id()),homepicList.get(i).getH_name());
			}
			else {
			homepicMap.put(Integer.toString(homepicList.get(i).getH_Id()), homepicList.get(i).getH_name());
			}
		}
		
		model.addAttribute("homepicMain",hompicMain);
		model.addAttribute("homepicMap",homepicMap);
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
	
		
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Locale locale, Model model) {
		logger.debug("login page........");
		return "login";
	}

	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public String email(Locale locale, Model model) {
		logger.debug("email page.........");
		return "email";
		
	}

	@RequestMapping(value = "/contact")
	public String about(Locale locale, Model model) {
		logger.debug("contact page.........");
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
	
		
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		return "contact";
	}

	@RequestMapping(value = "/logout")
	public String logout(Locale locale, Model model) {
		logger.debug("logout page.........");
		return "logout";
	}

	@RequestMapping(value = "/upload")
	public String upload(Locale locale, Model model) {
		logger.debug("logout page.........");
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
	
		
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		
		return "upload";
	}

	@RequestMapping(value = "/editImage")
	public String edit(Locale locale, Model model) {
		logger.debug("editImage page.........");
		return "editImage";
	}
	
	@RequestMapping(value = "/editCategory")
	public String editCategory(Locale locale, Model model) {
		logger.debug("editCategory page.......");
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
	
		
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		model.addAttribute("photo", categoryDAO.count("photo").get(0));
		model.addAttribute("commercial", categoryDAO.count("commercial").get(0));
		return "editCategory";
	}
	
	@RequestMapping(value="/editCategoryApply", method = RequestMethod.POST)
	public String editCategoryApply(Locale locale, Model model, 
			@RequestParam("photo") String photo, @RequestParam("commercial") String commercial) {
		logger.debug("editCategoryApply page.....");
		String[] pOrder = photo.split(","); //쨩챌쩔챘�횣째징 횁철횁짚횉횗 쩌첩쩌짯
		String[] cOrder = commercial.split(",");
		
		List<PhotoDTO> photoList = photoDAO.selectPhotoList();
		List<CommeDTO> commeList = commeDAO.selectCommeList();
		String[] photo_order = new String[categoryDAO.count("photo").get(0)];
		String[] comme_order = new String[categoryDAO.count("commercial").get(0)];
		
		for(int i=0;i<photoList.size();i++) {
			if(pOrder[photoList.get(i).getP_cate_order()].equals("done")) continue;
			PhotoDTO photoDTO = new PhotoDTO();
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCate_type("photo");
			photoDTO.setP_Category(photoList.get(i).getP_Category());
			categoryDTO.setCate_name(photoList.get(i).getP_Category());
			photoDTO.setP_cate_order(Integer.valueOf(pOrder[photoList.get(i).getP_cate_order()]));
			categoryDTO.setCate_order(Integer.valueOf(pOrder[photoList.get(i).getP_cate_order()]));
			pOrder[photoList.get(i).getP_cate_order()] = "done";
			photo_order[photoDTO.getP_cate_order()] = photoList.get(i).getP_Category();
			photoDAO.updatePhotoCategory(photoDTO);
			categoryDAO.updateCateOrder(categoryDTO);
		}
		
		for(int i=0;i<commeList.size();i++) {
			if(cOrder[commeList.get(i).getC_cate_order()].equals("done")) continue;
			CommeDTO commeDTO = new CommeDTO();
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setCate_type("commercial");
			commeDTO.setC_Category(commeList.get(i).getC_Category());
			categoryDTO.setCate_name(commeList.get(i).getC_Category());
			commeDTO.setC_cate_order(Integer.valueOf(cOrder[commeList.get(i).getC_cate_order()]));
			categoryDTO.setCate_order(Integer.valueOf(cOrder[commeList.get(i).getC_cate_order()]));
			cOrder[commeList.get(i).getC_cate_order()] = "done";
			comme_order[commeDTO.getC_cate_order()] = commeList.get(i).getC_Category();
			commeDAO.updateCommeCategory(commeDTO);
			categoryDAO.updateCateOrder(categoryDTO);
		}
	
		model.addAttribute("photoCategory", photo_order);
		model.addAttribute("comCategory", comme_order);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/mailSending")
	public String mailSending(Locale locale, Model model) {
		logger.debug("mailSending page.........");
		return "mailSending";
	}
	
	@RequestMapping(value="/updatehomepic", method={RequestMethod.GET,RequestMethod.POST})
	public String updatehomepic(Model model, 
			@RequestParam("home_id")int home_id,
			@RequestParam("photofile")String photo_real_name) {
		String urlarr[] = photo_real_name.split("/");
		String homepic_name = urlarr[5];

		HomepicDTO homepicDTO = new HomepicDTO();
		homepicDTO.setH_Id(home_id);
		homepicDTO.setH_name(homepic_name);
		homepicDAO.updateHome(homepicDTO);
		return "redirect:/";
	}
	

	/*
	 * @RequestMapping(value="/deletehomepic",
	 * method={RequestMethod.GET,RequestMethod.POST}) public String
	 * deletehomepic(Model model,
	 * 
	 * @RequestParam("home_id")int home_id) { homepicDAO.deleteHome(home_id); return
	 * "redirect:/home"; }
	 */
}
