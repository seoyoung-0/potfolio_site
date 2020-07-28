<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cPath = request.getContextPath();
	session.setAttribute("idKey", "이승채");
	String url = cPath + "/";
	String msg = "관리자 모드로 로그인합니다.";
%>
<!-- 관리자 모드로 로그인하면, HOME 화면 으로 이동  -->
<script type="text/javascript">
	alert("<%=msg%>");
	top.document.location.reload();
	location.href = "<%=url%>";
</script>
