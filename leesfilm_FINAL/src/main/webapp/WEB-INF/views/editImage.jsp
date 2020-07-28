<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <%
   request.setCharacterEncoding("UTF-8");
   String type = (String)session.getAttribute("type");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload Images/Videos</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
 
<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link href="<c:url value="/resources/css/styles.css?after" />" rel="stylesheet">
<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script type="text/javascript">
     window.onload= function(){
        var delete_photo_id = window.location.search;
        console.log(delete_photo_id);
     }
     
     function submitPhoto(index){
     	 if (index == 1)
     		 document.form.action = "/updatephoto";
     	 if (index == 2)
     		 document.form.action = "/deletephoto";
     	 
     	 document.form.submit();
     }

     function submitFilms(index){
     	 if (index == 1)
     		 document.form.action = "/updatefilm";
     	 if (index == 2)
     		 document.form.action = "/deletefilm";
     	 
     	 document.form.submit();	
     }
     
     function submitComme(index){
     	 if (index == 1)
     		 document.form.action = "/updatecomme";
     	 if (index == 2)
     		 document.form.action = "/deletecomme";
     	 
     	 document.form.submit();
     }
     
     function submitHomepic(index){
    	 if (index == 1)
    		 document.form.action = "/updatehomepic";
    	 if (index == 2)
    		 document.form.action = "/deletehomepic";
    	 
    	 document.form.submit();
     }
     
     function reloadParent(){
    	 window.self.close();  
    	 window.opener.document.location.href = window.opener.document.URL;
      }

</script>          
</head>
<body onunload="reloadParent()">
 
<div class="container" id="upload_container">
   <form id="form" name="form" method="post" enctype="multipart/form-data">
      <table class="table table-bordered">
       <tbody>
       <%
       Object photo_id= request.getParameter("photo_id");
       Object film_id = request.getParameter("film_id");
       Object comme_id = request.getParameter("comme_id");
       Object home_id = request.getParameter("home_id");
       %>
       <form name="form" method="post" encType="multiplart/form-data">
            <% if(type == "image") { %>
            <tr>
                <th id="upload_th">사진 첨부 </th>
                <td><input type="text" placeholder="이미지 링크를 입력하세요" id="upload_file" name="photofile" id="photoImg" class="form-control" /></td>
            </tr>
            <% } else if(type == "films") { %>
            <tr>
                <th id = "upload_th">영상 링크 </th>
                <td><input type="text" placeholder="영상 링크를 입력하세요" name="video_film_url" class="form-control" /></td>
            </tr>
            <% } else if(type == "comme") { %>
            <tr>
                <th id = "upload_th">영상 링크 </th>
                <td><input type="text" placeholder="영상 링크를 입력하세요" name="video_comme_url" class="form-control" /></td>
            </tr>
            <% } else if(type == "homepic") { %>
            <tr>
                <th id = "upload_th">사진 첨부 </th>
                <td><input type="text" placeholder="이미지 링크를 입력하세요" id="upload_file" name="photofile" id="photoImg" class="form-control" /></td>
            </tr>
            <% } %>
            <tr>
             <input type=hidden name="photo_id" value="<%=photo_id%>">
             <input type=hidden name="film_id" value="<%=film_id%>">
             <input type=hidden name="comme_id" value="<%=comme_id%>">
             <input type=hidden name="home_id" value="<%=home_id%>">
                <td colspan="2">
                 <% if(type=="image") {%>
                 	<input id="upload_btn" type="button" value="수정" class="pull-right" onclick='submitPhoto(1)'/>
                 	<input id="upload_btn" type="reset" class="pull-left"/>
                 	<input id="upload_btn" type="button" value="삭제" class="pull-right" onclick='submitPhoto(2)'/>
	             <% } else if (type == "films") {%>
	              	<input id="upload_btn" type="button" value="수정" class="pull-right" onclick='submitFilms(1)'/>
                  	<input id="upload_btn" type="reset" class="pull-left"/>
	                <input id="upload_btn" type="button" value="삭제" class="pull-right" onclick='submitFilms(2)'/>
	             <% } else if (type == "comme") {%>
					<input id="upload_btn" type="button" value="수정" class="pull-right" onclick='submitComme(1)'/>
                  	<input id="upload_btn" type="reset" class="pull-left"/>
	                <input id="upload_btn" type="button" value="삭제" class="pull-right" onclick='submitComme(2)'/>
	             <% } else if (type == "homepic") {%>
					<input id="upload_btn" type="button" value="수정" class="pull-right" onclick='submitHomepic(1)'/>
                  	<input id="upload_btn" type="reset" class="pull-left"/>
                 <% } %>
                </td>
            </tr>
       </tbody>
       </table>
    </form>
</div>
</body>
</html>
