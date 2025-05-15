<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>마이페이지</title>
  <style>
    #profileImg{
      width:250px;
      height:250px;
      border:1px solid lightgray;
      border-radius: 50%;
    }
  </style>
</head>
<body>

  <div class="container p-3">

    <!-- Header, Nav start -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <!-- Header, Nav end -->

    <!-- Section start -->
    <section class="row m-3" style="min-height: 500px">

      <div class="container border p-5 m-4 rounded">
        <h2 class="m-4">마이페이지</h2>
        <br>

        <div align="center">
          <img id="profileImg" src="${contextPath}<c:out value="${loginUser.profileURL}" default="/resources/images/defaultProfile.png"/>" onclick="document.getElementById('profileImgFile').click();">
          <input type="file" class="file" id="profileImgFile" style="display:none;"> <%-- 파일 선택하는 순간 change 이벤트 동작 --%>
        </div>

        <script>
          document.getElementById('profileImgFile').addEventListener('change', evt => {
            const fileInput = evt.target;

            /*
            fileInput.files : FileList 객체 [File, File, ...]
            fileInput.files.length : 선택된 파일의 개수
            fileInput.files[0] : 선택된 첫번쨰 파일 객체
             */
            if (fileInput.files.length != 0) {

              let formData = new FormData();
              formData.append('uploadFile', fileInput.files[0]);

              fetch('${contextPath}/user/modifyProfile.do', {
                method: 'post',
                body: formData
              })
                .then(response => response.text())
                .then(data => {
                  location.reload(); // 비동기로 url 재요청
                })

            }
          })
        </script>

        <form action="${contextPath}/user/registUser.do" method="POST" id="modify_form">
          <div class="form-group">
            <label for="userId">* ID :</label>
            <input type="text" class="form-control" id="userId" name="userId" value="${loginUser.userId}" readonly><br>

            <label for="userName">* Name :</label>
            <input type="text" class="form-control" id="userName" name="userName" value="${loginUser.userName}"><br>

            <label for="email"> &nbsp; Email :</label>
            <input type="email" class="form-control" id="email" name="email" value="${loginUser.email}"><br>

            <label for="phone"> &nbsp; Phone :</label>
            <input type="tel" class="form-control" id="phone" name="phone" value="${loginUser.phone}"><br>

            <label for="address"> &nbsp; Address :</label>
            <input type="text" class="form-control" id="address" name="address" value="${loginUser.address}"><br>

            <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
            <input type="radio" name="gender" id="Male" value="M">
            <label for="Male">남자</label> &nbsp;&nbsp;
            <input type="radio" name="gender" id="Female" value="F">
            <label for="Female">여자</label><br>

            <script>
              document.querySelector("#modify_form input[value=${loginUser.gender}]").checked = true;
            </script>

          </div>
          <br>
          <div class="btns" align="center">
            <button type="submit" class="btn btn-primary">수정하기</button>
            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#deleteForm">회원탈퇴</button>
          </div>
        </form>

      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <!-- Footer end -->

  </div>

  <!-- 회원탈퇴 버튼 클릭시 보여질 Modal -->
  <div class="modal" id="deleteForm">
    <div class="modal-dialog">
      <div class="modal-content">

        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">회원탈퇴</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>

        <!-- Modal body -->
        <div class="modal-body" align="center">

          <b>
            탈퇴 후 복구가 불가능합니다. <br>
            정말로 탈퇴 하시겠습니까?
          </b>

          <form action="탈퇴요청url" method="POST">
            비밀번호 :
            <input type="password" name="" required>

            <button type="submit" class="btn btn-danger">탈퇴하기</button>
          </form>

        </div>

      </div>
    </div>
  </div>



</body>
</html>
