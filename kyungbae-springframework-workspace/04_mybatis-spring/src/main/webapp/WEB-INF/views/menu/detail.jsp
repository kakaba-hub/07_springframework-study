<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Menu Detail</title>
</head>
<body>
  <script>
    <c:if test="${not empty message}">
    alert("${message}");
    </c:if>
  </script>

  <h1>메뉴 상세정보</h1>
  <c:choose>
    <c:when test="${empty menu}">
      * 조회된 메뉴가 없습니다.
    </c:when>
    <c:otherwise>
      * 조회된 메뉴의 상세정보 <br>
      메뉴코드: ${menu.menuCode} <br>
      메뉴명: ${menu.menuName} <br>
      가격: ${menu.menuPrice} <br>
      카테고리: ${menu.categoryCode} <br>
      판매여부: ${menu.orderableStatus == 'Y' ? '판매중' : '미판매'} <br> <br>
      <button onclick="location.href='${contextPath}/menu/modify.page?code=${menu.menuCode}';">메뉴 수정 페이지로 이동</button>
      <button onclick="location.href='${contextPath}/menu/list.page';">목록으로</button>
    </c:otherwise>
  </c:choose>
</body>
</html>
