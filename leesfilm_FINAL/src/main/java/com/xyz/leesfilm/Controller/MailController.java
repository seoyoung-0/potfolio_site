package com.xyz.leesfilm.Controller;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailController {
	
	// mailSending 코드
		@RequestMapping(value = "/mailSend", method=RequestMethod.POST)
		public void mailSending(Model model,
				@RequestParam("name") String name,
				@RequestParam("email") String email,
				@RequestParam("message") String body,
				HttpServletResponse response) throws Exception {
			
			//메일 관련 정보
			String host = "smtp.naver.com";
			final String username = "email";
			final String password = "password"; //나중에 맞는 비밀번호로 바꾸기
			int port=465;
			String recipient = "email@naver.com";
			
			String title = name + "님으로부터 메일이 도착했습니다.";
			String msg = "보낸 분 이메일: " + email + "\n" + "내용: \n" + body;
			
			Properties props = System.getProperties();
			
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.enable", "true");
			props.put("mail.smtp.ssl.trust", host);
			
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = username;
				String pw = password;
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(un, pw);
				}
			});
			session.setDebug(true);
			
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(recipient));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			mimeMessage.setSubject(title);
			mimeMessage.setText(msg);
			Transport.send(mimeMessage);
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('성공적으로 메일이 보내졌습니다'); location.href='./';</script>");
			out.flush();
			
		}
}
