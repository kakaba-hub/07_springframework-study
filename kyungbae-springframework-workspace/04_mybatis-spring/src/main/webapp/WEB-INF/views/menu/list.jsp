<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>Menu List</title>
</head>
<body>

  <c:if test="${not empty message}">
    <script>alert('${message}');</script>
  </c:if>

  <h2>판매중인 메뉴 목록</h2>

  <a href="${contextPath}/menu/regist.page">신규 메뉴 등록 페이지로 이동</a>

  <form action="${contextPath}/menu/remove.do" method="post">
    <button type="submit">삭제</button>
    <table border="1">
      <thead>
      <tr>
        <th>선택</th>
        <th>번호</th>
        <th>메뉴명</th>
        <th>메뉴가격</th>
        <th>카테고리번호</th>
      </tr>
      </thead>
      <tbody>
      <c:choose>
        <c:when test="${empty list}">
          <tr>
            <td colspan="5" style="text-align: center">판매가능한 메뉴가 존재하지 않습니다.</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach var="a" items="${list}">
            <tr>
              <td><input type="checkbox" name="code" value="${a.menuCode}"></td>
              <td>${a.menuCode}</td>
              <td><a href="${contextPath}/menu/detail.page?code=${a.menuCode}">${a.menuName}</a></td>
              <td>${a.menuPrice}</td>
              <td>${a.categoryCode}</td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>

      </tbody>
    </table>
  </form>
</body>
</html>
