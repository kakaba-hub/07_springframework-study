<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입페이지</title>
  <style>
    .uncheck{display:none;}
    .smallfont{font-size:0.8em;}
    .usable{color:green;}
    .unusable{color:red;}
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
        <h2 class="m-4">회원가입</h2>
        <br>

        <form action="${contextPath}/user/signup.do" method="POST" id="signup_form">
          <div class="form-group">
            <label for="userId">* ID :</label>
            <input type="text" class="form-control" id="userId" name="userId" placeholder="Please Enter ID" required>
            <div id="idcheck_result" class="uncheck smallfont"></div>
            <br>

            <label for="userPwd">* Password :</label>
            <input type="password" class="form-control" id="userPwd" name="userPwd" placeholder="Please Enter Password"
                   required><br>

            <label for="checkPwd">* Password Check :</label>
            <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required><br>

            <label for="userName">* Name :</label>
            <input type="text" class="form-control" id="userName" name="userName" placeholder="Please Enter Name" required><br>

            <label for="email"> &nbsp; Email :</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="Please Enter Email"><br>

            <label for="phone"> &nbsp; Phone :</label>
            <input type="tel" class="form-control" id="phone" name="phone" placeholder="Please Enter Phone (-포함)"><br>

            <label for="address"> &nbsp; Address :</label>
            <input type="text" class="form-control" id="address" name="address" placeholder="Please Enter Address"><br>

            <label for=""> &nbsp; Gender : </label> &nbsp;&nbsp;
            <input type="radio" name="gender" id="Male" value="M">
            <label for="Male">남자</label> &nbsp;&nbsp;
            <input type="radio" name="gender" id="Female" value="F">
            <label for="Female">여자</label><br>
          </div>
          <br>
          <div class="btns" align="center">
            <button type="submit" class="btn btn-primary" disabled>회원가입</button>
            <button type="reset" class="btn btn-danger"> 초기화</button>
          </div>
        </form>

        <script>
          // 동적으로 제어할 요소
          const msgResult = document.querySelector('#signup_form #idcheck_result');
          const submitBTN = document.querySelector('#signup_form [type=submit]'); // 회원가입버튼

          function statusChange(flag) {
            if (flag == 1) {
              msgResult.classList.add('usable');
              msgResult.classList.remove('unusable');
              submitBTN.removeAttribute('disabled');
            } else if (flag == 2) {
              msgResult.classList.add('unusable');
              msgResult.classList.remove('usable');
              submitBTN.setAttribute('disabled', 'disabled');
            }
          }

          document.querySelector('#signup_form #userId').addEventListener('keyup', evt => {

            const userIdInput = evt.target; // 아이디입력란 input 요소

            if (userIdInput.value.trim().length == 0) {
              msgResult.className = 'uncheck smallfont';
            } else {
              msgResult.classList.remove('uncheck');

              const regExp = /^[a-z\d]{5,12}$/;
              if (regExp.test(userIdInput.value)) { // 패턴에 부합할 경우 id 중복 조회 (비동기)

                fetch('${contextPath}/user/idcheck.do?checkId=' + userIdInput.value)
                  .then(response => response.text())
                  .then(data => {
                    if (data == 'NOTUSED') {
                      msgResult.textContent = "사용 가능한 아이디입니다.";
                      statusChange(1);
                    } else {
                      msgResult.textContent = "중복된 아이디입니다.";
                      statusChange(2);
                    }
                  })

              } else { // 패턴에 부합하지 않을 경우
                msgResult.textContent = '영문, 숫자 포함 5~12자리로 작성해주세요';
                statusChange(2);
              }
            }
          });

        </script>

      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <!-- Footer end -->

  </div>


</body>
</html>
