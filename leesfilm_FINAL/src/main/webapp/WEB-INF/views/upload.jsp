<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
   request.setCharacterEncoding("UTF-8");
String id = (String) session.getAttribute("idKey");
if (id != "이승채") {
%>
<script>
        alert("관리자모드로 로그인해주세요!");
        location.href="/contact";
</script>
<%
   }
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload Images/Videos</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
   integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
   crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
   integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
   crossorigin="anonymous">
<link href="<c:url value="/resources/css/styles.css" />"
   rel="stylesheet">
<!-- Latest compiled and minified JavaScript -->

<script type="text/javascript">
// 대분류
var firstList = new Array("Photo", "Commercial", "Films");

// 중분류
								
var secondList1 = new Array("소분류를 선택하세요.");
//,"Photo1","Photo2","Photo3","소분류 추가");
<c:forEach items="${photoCategory}" var="subphotos">
secondList1.push("${subphotos}")
</c:forEach>
secondList1.push("소분류 추가");
var secondList2 = new Array("소분류를 선택하세요.");
<c:forEach items="${comCategory}" var="subcoms">
secondList2.push("${subcoms}")
</c:forEach>
secondList2.push("소분류 추가");
//var secondList3 = new Array("소분류를 선택하세요.","소분류 추가");
  
var d_flag = false;
var a_flag = false;

// 페이지 로딩시 자동 실행  
window.onload = function(){
    var v_sidoSelect = document.getElementById("sidoSelect"); // SELECT TAG
      
    for (i =0 ; i<firstList.length; i++){// 0 ~ 3 
        // 새로운 <option value=''>값</option> 태그 생성
        var optionEl = document.createElement("option");
        // option태그에 value 속성 값으로 저장
        optionEl.value = firstList[i];
        // text 문자열을 새로 생성한 <option> 태그의 값으로 추가
        optionEl.appendChild (document.createTextNode(firstList[i]));
        // 만들어진 option 태그를 <select>태그에 추가
        v_sidoSelect.appendChild(optionEl);
    }
  
    var v_gugunSelect = document.getElementById("gugunSelect"); // SELECT TAG
    v_gugunSelect.style.display = "none";  // 태그 감추기

}

// 대분류 선택시
function changeSidoSelect(){
    var v_sidoSelect = document.getElementById("sidoSelect"); // SELECT TAG
    var idx = v_sidoSelect.options.selectedIndex;     // 선택값 0 ~ 3
     

    if (idx < 1 && idx > 3){
        return;
    }
    
    if(d_flag){
      var v_delSelect = document.getElementsByName("deleteCategory")[0];
        v_delSelect.parentNode.removeChild(v_delSelect);
        d_flag = false;
   }
   if(a_flag) {
      var v_addSelect = document.getElementById("addCategory");
        v_addSelect.parentNode.removeChild(v_addSelect);
        a_flag = false;   
   }
   
    gugunSelectFill(idx);   // 중분류 생성
}


function gugunSelectFill(idx){
    var v_gugunSelect = document.getElementById("gugunSelect"); // SELECT TAG
    
    var data = null;
  
    if (idx == 1){ //대분류 photo 일 때, 생기는 소분류 secondList1
       //db 에는 소분류 카테고리만 전달 
     data = secondList1
   
     }
    if (idx == 2){
     data = secondList2
     
     }
    //if (idx == 3) data = secondList3;
  

    v_gugunSelect.innerHTML = "";  // 태그 출력
      
    for (i =0 ; i<data.length; i++){ 
        // 새로운 <option value=''>값</option> 태그 생성
        var optionEl = document.createElement("option");
  
        // value 속성 태그에 저장
        optionEl.value = data[i];
      
        // text 문자열을 새로 생성한 <option> 태그에 추가
        optionEl.appendChild (document.createTextNode(data[i]));
      
        // 만들어진 option 태그를 <select>태그에 추가
        v_gugunSelect.appendChild(optionEl);
    }
  
v_gugunSelect.style.display = ""; // 중분류 태그 출력

}

// 대분류 선택시
function changeSecondSelect(){
    var v_sidoSelect = document.getElementById("sidoSelect"); // SELECT TAG
    var v_secondSelect = document.getElementById("gugunSelect"); // SELECT TAG
    var idx = v_sidoSelect.options.selectedIndex; 

    if (idx < 0 && idx > 2){
        return;
    }
    
   /* if(v_sidoSelect.options[v_sidoSelect.selectedIndex].value == "Films") {
      return;
    } */
   
    var v_next = v_secondSelect.options[v_secondSelect.selectedIndex].value;
    if(v_next == "소분류를 선택하세요."){
       if(d_flag){
          var v_delSelect = document.getElementsByName("deleteCategory")[0];
            v_delSelect.parentNode.removeChild(v_delSelect);
            d_flag = false;
       }
       if(a_flag) {
          var v_addSelect = document.getElementById("addCategory");
            v_addSelect.parentNode.removeChild(v_addSelect);
            a_flag = false;   
       }
    }
    else if(v_next == "소분류 추가"){
       addSecondSelect();
       if(d_flag){
          var v_delSelect = document.getElementsByName("deleteCategory")[0];
            v_delSelect.parentNode.removeChild(v_delSelect);
            d_flag = false;
       }
    }
    else{
       deleteSelect();
       if(a_flag) {
          var v_addSelect = document.getElementById("addCategory");
            v_addSelect.parentNode.removeChild(v_addSelect);
            a_flag = false;   
       }
    }
}



function addSecondSelect(){
   if(a_flag) return;
    var v_addSelect = document.getElementById("sido");
    
      var optionEl = document.createElement("input");
    optionEl.setAttribute("type", "text");
    optionEl.setAttribute("id", "addCategory");
    optionEl.setAttribute("name", "addCategory");
    optionEl.setAttribute("style", "color:black; font-size: 15px;");
    
    v_addSelect.appendChild(optionEl);
    a_flag = true;
}

function deleteSelect(){
   if(d_flag) return;
    var v_deleteSelect = document.getElementById("sido");
    
    var optionEl = document.createElement("input");
    optionEl.setAttribute("type", "button");
    optionEl.setAttribute("id", "upload_btn");
    optionEl.setAttribute("value", "삭제하기");
    optionEl.setAttribute("name", "deleteCategory");
    optionEl.setAttribute("onclick", "delCate()");
    
    v_deleteSelect.appendChild(optionEl);
    d_flag = true;
}

function delCate(){
	var v = document.getElementById("gugunSelect").value;
	var check = confirm("정말로 "+v+" 카테고리 전체를 삭제하겠습니까? 삭제하면 복구할 수 없습니다.");
	if(!check) return;
	var v_type = document.getElementById("sidoSelect").value;
	if(v_type == "Photo"){
		document.form.action = "/deletephotocategory";
	} else if(v_type == "Commercial"){
		document.form.action = "/deletecommecategory";
	}
   document.form.submit();
}

function subType(){
   var v_type = document.getElementById("sidoSelect").value;
   if(document.getElementById("gugunSelect").value == "소분류를 선택하세요."){
	   alert("소분류를 선택해 주세요.");
       return;
   }
   
   if(v_type == "Photo"){
      if(document.form.photofile.value == ""){
         alert("사진을 첨부해 주세요.");
         document.form.photofile.focus();
         return;
      }
      if(document.form.video_url.value != ""){
         alert("영상 링크를 지워주세요.");
         document.form.video_url.focus();
         return;
      }
      document.form.action = "/uploadphoto";
   }else if(v_type == "Commercial" || v_type == "Films"){
      if(document.form.video_url.value == ""){
         alert("영상 링크를 첨부해 주세요.");
         document.form.video_url.focus();
         return;
      }
      if(document.form.photofile.value != ""){
         alert("사진을 지워주세요.");
         document.form.photofile.focus();
         return;
      }
      
      if(v_type == "Commercial"){
         document.form.action = "/uploadCommercial";
      }else {
         document.form.action = "/uploadFilms";
      }
      
   }
   document.form.submit();
}
</script>

</head>
<body>
   <div class="container" id="upload_container">
      <form id="form" name="form" method="post" enctype="multipart/form-data">
         <table class="table table-bordered">
            <tbody>
               <tr>
                  <th id="upload_th">카테고리</th>
                  <td>
                     <div id="sido">
                        <select id="sidoSelect" name="sidoSelect" onChange="changeSidoSelect();">
                           <option value="">대분류를 선택하세요.</option>
                        </select>
                        <select id="gugunSelect" name="gugunSelect" onChange="changeSecondSelect();">
                           <option value="">중분류 선택하세요.</option>
                        </select>
                     </div>
                     
                  </td>
               </tr>
               <tr>
                  <th id="upload_th">사진 첨부</th>
                  <td>
                     <!-- <input id="upload_file" type="file" name="photofile" id="photoImg"/>-->
                     <input type="text" placeholder="이미지 링크를 입력하세요" id="upload_file" name="photofile" id="photoImg" class="form-control" />
                  </td>
               </tr>
               <tr>
                  <th id="upload_th">영상 링크</th>
                  <td><input type="text" placeholder="영상 링크를 입력하세요" name="video_url" class="form-control" /></td>
               </tr>
               <tr>
                  <td colspan="2">
                     <input id="upload_btn" type="button" value="등록" class="pull-right submit" onclick="subType()" /> 
                     <input id="upload_btn" type="reset" class="pull-left" /> 
                     <input id="upload_btn" type="button" value="뒤로가기" class="pull-right" onclick="javascript:location.href='./'" /> 
                     <!-- <a class="btn btn-default" onclick="sendData()"> 등록 </a>
                          <a class="btn btn-default" type="reset"> reset </a>
                          <a class="btn btn-default" onclick="javascript:location.href='list.jsp'">글 목록으로...</a> -->
                  </td>
               </tr>
            </tbody>
         </table>
      </form>
   </div>
   <footer class="footer"></footer>
</body>
</html>