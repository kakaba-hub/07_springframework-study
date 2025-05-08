<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>main page</title>
  <script src="${contextPath}/resources/js/sample.js"></script>
</head>
<body>
  <%-- http://localhost:8080/webmvc/ url 요청시 /WEB-INF/views/main.jsp가 보여지도록 포워딩 --%>
  <h1>메인 페이지 입니다.</h1>

  <h3>1. 정적 자원(이미지 등) 확인</h3>
  <%-- 상대경로 --%>
  <%-- <img src="assets/image/flower1.jpg" width="200">--%>
  <%-- 절대경로 --%>
  <img src="${contextPath}/assets/image/flower1.jpg" width="200" onclick="test();">

  <hr>

  <h3>2. 응답페이지 포워딩 또는 redirect 처리</h3>
    <a href="${contextPath}/book/list.do">도서 목록 페이지로 이동</a>

</body>
</html>
