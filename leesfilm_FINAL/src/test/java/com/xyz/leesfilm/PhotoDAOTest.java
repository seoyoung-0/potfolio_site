package com.xyz.leesfilm;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xyz.leesfilm.DAO.PhotoDAO;
import com.xyz.leesfilm.DTO.PhotoDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class PhotoDAOTest {
	
	@Inject
	private PhotoDAO dao;
	
	@Test
	public void testInsertPhoto() throws Exception{
		PhotoDTO dto = new PhotoDTO();
		dto.setP_Category("fleshes");
		dto.setP_Name("test.jpg");
	//	dto.setP_RealName("test1.jpg");
		dao.insertPhoto(dto);
	}

}
