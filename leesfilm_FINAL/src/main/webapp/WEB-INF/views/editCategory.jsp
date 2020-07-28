<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <%
   request.setCharacterEncoding("UTF-8");
   String del = (String)session.getAttribute("delete");
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
     
     
     
     var photoList = new Array();
     var commeList = new Array();
     
     <c:forEach items="${photoCategory}" var="subphotos">
     photoList.push("${subphotos}")
     </c:forEach>
     <c:forEach items="${comCategory}" var="subcoms">
     commeList.push("${subcoms}")
     </c:forEach>
     
</script>       
</head>
<body>
<script type="text/javascript">
function editCategory(){
	
	var pList = document.getElementsByName("photo");
	var cList = document.getElementsByName("commercial");
	var photo = ${photo} - 1;
	var commercial = ${commercial} - 1;
	var pOrder = new Array();
	for(i=0;i<=photo;i++){
		pOrder[i] = "false";
	}
	var cOrder = new Array();
	for(i=0;i<=commercial;i++) cOrder[i] = "false";
	for(i=0;i<pList.length;i++){
		if(pList[i].value>photo || pOrder[pList[i].value]==true){
			alert("Photo Category의 순서는 0 ~ "+ photo +"의 숫자로 중복이나 빈 칸 없이 입력해주세요.");
	        return;
		}
		pOrder[pList[i].value] = true;
	}
	
	for(i=0;i<cList.length;i++){
		if(cList[i].value>commercial || cOrder[cList[i].value]==true){
			alert("Commercial Category의 순서는 0 ~ " + commercial + "의 숫자로 중복이나 빈 칸 없이 입력해주세요.");
			return;
		}
		cOrder[cList[i].value] = true;
	}

	document.form.action = "/editCategoryApply";
	document.form.submit();
}

function submitFilms(index){
	 if (index == 1)
		 document.form.action = "/updatefilm";
	 if( index == 2)
		 document.form.action = "/deletefilm";
	 
	 document.form.submit();	
}
function submitComme(index){
	 if (index == 1)
		 document.form.action = "/updatecomme";
	 if( index == 2)
		 document.form.action = "/deletecomme";
	 
	 document.form.submit();
}

</script>       
<div class="container" id="upload_container">
   <form id="form" name="form" method="post" enctype="multipart/form-data">
      <table class="table table-bordered">
       <tbody>
       <%
       Object photo_id= request.getParameter("photo_id");
       Object film_id = request.getParameter("film_id");
       Object comme_id = request.getParameter("comme_id");
       %>
     
           <tr><td colspan="2">Photo Category</td></tr>
          <c:set var="order" value="0"/>
           <c:forEach items="${photoCategory}" var="subphotos"> 
								<tr><td>${subphotos}</td>
								<td>
						
							<input name="photo" type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="${order}" id="upload_file" class="form-control" numberOnly/></td></tr>
					
						<c:set var="order" value="${order+1}"/>
								 </c:forEach> 
           
            <tr>
               <td colspan="2">Commercial Category</td>
                
            </tr>
             <c:set var="order" value="0"/>
          
           <c:forEach items="${comCategory}" var="subcoms"> 
								<tr><td>${subcoms}</td>
								<td>
                <input name="commercial" type="text" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="${order}" id="upload_file" class="form-control" numberOnly/></td>
                </tr>
              <c:set var="order" value="${order+1}"/>
								 </c:forEach> 
           
         
        
            <tr>
         <%--     <input type=hidden name="photo_id" value="<%=photo_id%>">
             <input type=hidden name="film_id" value="<%=film_id%>">
             <input type=hidden name="comme_id" value="<%=comme_id%>"> --%>
                <td colspan="2">
                
                 	<input id="upload_btn" type="button" value="수정" class="pull-right" onclick='editCategory()'/>
                 	<input id="upload_btn" type="reset" class="pull-left"/>
                 <%-- 	<%if(del == "true") {%>
	                	<input id="upload_btn" type="button" value="삭제" class="pull-right" onclick='submitPhoto(2)'/>
	             	<% } %> --%>
	             
	           
                </td>
            </tr>
       </tbody>
       </table>
    </form>
</div>
</body>
</html>