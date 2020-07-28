<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" /> <!--  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
    request.setCharacterEncoding("UTF-8");
    String id = (String)session.getAttribute("idKey");
%>
<head>
<meta charset="utf-8" /> <!-- 여기부터 -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<!-- <meta name="viewport" content="width=device-width, minimum-scale=320"> --> 
<link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Merriweather+Sans:400,700"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css?family=Merriweather:400,300,300italic,400italic,700,700italic"
	rel="stylesheet" type="text/css" />
<!-- Third party plugin CSS-->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/magnific-popup.min.css"
	rel="stylesheet" />

<!-- Bootstrap core JS-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
<!-- Third party plugin JS-->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/magnific-popup.js/1.1.0/jquery.magnific-popup.min.js"></script>
<!-- 여기까지 -->
<link href="<c:url value="/resources/css/styles.css" />" rel="stylesheet" />
<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet" />

<script type="text/javascript">
	$(document).ready(function(e) {
	      $(document).on("click", "img", function() {
	         var path = $(this).attr('src')
	         var home_id=$(this).attr('title')
	         console.log(home_id);
	         
	         if(path=="https://raw.githubusercontent.com/eunbi14/WEB_DEV_PORTFOLIO/master/done/images/top3.png"){
	             window.scrollTo(0,0);
	              return;
	         }
	         if("<%=id%>" == "이승채") {
	            var result = confirm("이미지를 수정하겠습니까? 삭제는 안돼요 ~");
	            if(result){
	               <%
	               session.setAttribute("type","homepic");
	               %>
	               window.open("/editImage?home_id="+home_id,"Edit Image","width=500,height=500");
	            }else{
	               alert("실행 취소 되었습니다.");
	            }
	         } 
	      });
	   });
</script>
</head>
<body>
<div class="nav-top">
			<%
			if (id == "이승채") {
			%>
			<div id="upload_icons">
				
				<svg id="icon" class="bi bi-image" width="1em" height="1em"
					viewBox="0 0 16 16" fill="currentColor"
					xmlns="http://www.w3.org/2000/svg" onClick="location.href='/upload'">
  					<path fill-rule="evenodd"
						d="M14.002 2h-12a1 1 0 00-1 1v10a1 1 0 001 1h12a1 1 0 001-1V3a1 1 0 00-1-1zm-12-1a2 2 0 00-2 2v10a2 2 0 002 2h12a2 2 0 002-2V3a2 2 0 00-2-2h-12z"
						clip-rule="evenodd" />
  					<path
						d="M10.648 7.646a.5.5 0 01.577-.093L15.002 9.5V14h-14v-2l2.646-2.354a.5.5 0 01.63-.062l2.66 1.773 3.71-3.71z" />
  					<path fill-rule="evenodd"
						d="M4.502 7a1.5 1.5 0 100-3 1.5 1.5 0 000 3z" clip-rule="evenodd" />
				</svg>
				&nbsp;
				<svg id="icon" width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-justify" fill="currentColor" xmlns="http://www.w3.org/2000/svg" onClick="location.href='/editCategory'">
  					<path fill-rule="evenodd" d="M2 12.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm0-3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/>
				</svg>
				&nbsp;
				<svg id="icon" class="bi bi-people-circle" width="1em" height="1em"
					viewBox="0 0 16 16" fill="currentColor"
					xmlns="http://www.w3.org/2000/svg" onClick="location.href='/logout'">
  					<path
						d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 008 15a6.987 6.987 0 005.468-2.63z" />
  					<path fill-rule="evenodd" d="M8 9a3 3 0 100-6 3 3 0 000 6z"
						clip-rule="evenodd" />
					<path fill-rule="evenodd"
						d="M8 1a7 7 0 100 14A7 7 0 008 1zM0 8a8 8 0 1116 0A8 8 0 010 8z"
						clip-rule="evenodd" />
				</svg>
			</div>
			<%
				}
			%>
			<nav class="navbar navbar-expand-lg navbar-light fixed-top py-3"
				id="mainNav">
				<div class="container">
					<a class="navbar-brand js-scroll-trigger" href="./">Seungchae Lee</a>
					<button class="navbar-toggler navbar-toggler-right" type="button"
						data-toggle="collapse" data-target="#navbarResponsive"
						aria-controls="navbarResponsive" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarResponsive">
						<ul class="navbar-nav ml-auto my-2 my-lg-0">
							<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="/">About</a></li>
							<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="#">Photo</a>
								<ul>
							 	<c:forEach items="${photoCategory}" var="subphotos"> 
									<li><a href="/photo/${subphotos}">${subphotos}</a></li>
								</c:forEach>
								</ul> 
							</li>
							<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="/films">Films</a>
							</li>
							<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="#">Commercial</a>
								<ul>
								<c:forEach items="${comCategory}" var="subcoms">
									<li><a href="/commercial/${subcoms}">${subcoms}</a></li>
								</c:forEach>
								</ul>	
							</li>
							<li class="nav-item"><a class="nav-link js-scroll-trigger"
								href="/contact">Contact</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<div style="position: fixed; bottom: 5px; right:5px; z-index:5;">
   			<a onClick="javascript:window.scrollTo(0,0)"><img style="width:50px;height:50px;" src="https://raw.githubusercontent.com/eunbi14/WEB_DEV_PORTFOLIO/master/done/images/top3.png" title="위로 가기"></a>
   		</div>
	
	<c:forEach items="${homepicMain}" var="Main">
    		<div class="title_img" id="t0">
		  		<c:set var="gd" value="http://drive.google.com/uc?export=view&amp;id="/>
				<c:set var="home_id" value="${Main.key}" />
				<c:set var="homesrc" value="${gd}${Main.value}" />
				<img src="${homesrc}" title="${home_id}"/>
			</div>
	</c:forEach>
	<div id="text">
		<p>Seoul to Paju</p>
		<br> <br>
		<p>Film</p>
		<p>Photo</p>
		<p>Video Art</p>
		<br> <br>
		<p>etc</p>
	</div>
	<div id="partition">
		<p>* * *</p>
	</div>

	<div class="sub_container">
		<c:forEach items="${homepicMap}" var="homepicMap">
    		<div class = "sub_img" id="v0">
		  		<c:set var="gd" value="http://drive.google.com/uc?export=view&amp;id="/>
				<c:set var="home_id" value="${homepicMap.key}" />
				<c:set var="homesrc" value="${gd}${homepicMap.value}" />
				<img src="${homesrc}" title="${home_id}"/>
			</div>
		</c:forEach>
	</div>

	<footer class="footer">
	<div id="instagram">
		<p>
			Follow <a id="insta" href="https://www.instagram.com/toomhme/" target="_blank">@toomhme</a> on Instagram
		</p>
	</div>	
	</footer>
</body>
</html>
