<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>main page</title>
</head>
<body>
  <h2>첨부파일 업로드 테스트</h2>

  <h4>1. 한개의 첨부파일 업로드 테스트</h4>
  <form action="${contextPath}/board/register1.do" method="post" enctype="multipart/form-data">
    게시글 제목: <input type="text" name="boardTitle"> <br>
    게시글 내용: <textarea name="boardContent"></textarea> <br>
    첨부파일: <input type="file" name="imageFile" accept="image/*"> <br>
    <input type="submit">
  </form>

  <h4>2. 여러개의 첨부파일 업로드 테스트</h4>
  <form action="${contextPath}/board/register2.do" method="post" enctype="multipart/form-data">
    게시글 제목: <input type="text" name="boardTitle"> <br>
    게시글 내용: <textarea name="boardContent"></textarea> <br>
    첨부파일: <input type="file" name="imageFile" accept="image/*" multiple> <br>
    <input type="submit">
  </form>

</body>
</html>
