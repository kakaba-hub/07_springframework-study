<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>main page</title>
</head>
<body>

  <h3>1. GET요청(파라미터-쿼리스트링), 문자열 응답</h3>
  아이디 : <input type="text" id="input1_1"> <br>
  비번   : <input type="text" id="input1_2"> <br>
  <button id="btn1">요청</button> <br>
  응답 : <span id="result1">요청1의 결과가 보여지는 영역</span>

  <script>
    document.getElementById("btn1").addEventListener("click", function (evt) {
      const id = document.getElementById('input1_1').value;
      const pw = document.getElementById('input1_2').value;

      // 쿼리스트링 제작
      const params = new URLSearchParams({id, pw}).toString();

      fetch("${contextPath}/ajaxTest1.do?" + params, {method: 'GET'})
        .then(response => response.text())
        .then(data => {
          console.log(data);
          document.getElementById('result1').textContent = data;
        })

    });
  </script>

  <hr>

  <h3>2. POST요청(파라미터-폼데이터), 문자열 응답</h3>
  아이디 : <input type="text" id="input2_1"> <br>
  비번   : <input type="text" id="input2_2"> <br>
  <button id="btn2">요청</button> <br>
  응답 : <span id="result2">요청2의 결과가 보여지는 영역</span>

  <script>
    document.getElementById("btn2").addEventListener("click", (evt) => {
      const id = document.getElementById("input2_1").value;
      const pw = document.getElementById("input2_2").value;

      const formData = new URLSearchParams();
      formData.append("id", id);
      formData.append("pw", pw);

      fetch("${contextPath}/ajaxTest2.do", {
        method: "post",
        headers: {
          "content-type": "application/x-www-form-urlencoded" // 폼데이터를 보내기위해 반드시 해야하는 설정
        },
        body: formData
      })
        .then(response => response.text())
        .then(data => {
          console.log(data);
          document.getElementById("result2").textContent = "data";
        })
    });
  </script>

  <hr>

  <h3>3. POST요청(파라미터-JSON문자열), 문자열 응답</h3>
  아이디 : <input type="text" id="input3_1"> <br>
  비번   : <input type="text" id="input3_2"> <br>
  <button id="btn3">요청</button> <br>
  응답 : <span id="result3">요청3의 결과가 보여지는 영역</span>

  <script>
    document.getElementById("btn3").addEventListener("click", (evt) => {
      const id = document.getElementById("input3_1").value;
      const pw = document.getElementById("input3_2").value;

      fetch("${contextPath}/ajaxTest3.do", {
        method: 'post',
        headers: {
          'content-type': 'application/json'
        },
        body: JSON.stringify({id: id, pw: pw}) // JSON 형식으로 변환
      })
        .then(response => response.text())
        .then(data => {
          document.getElementById("result3").textContent = data;
        })
    })
  </script>

  <hr>

  <h3>4. Java 객체(DTO) 응답</h3>
  번호 : <input type="number" id="input4">
  <button id="btn4">요청</button>
  <br>
  응답 :
  <div id="result4">요청4의 결과가 보여지는 영역</div>

  <script>

  </script>

  <hr>

  <h3>5. Java 객체(List) 응답</h3>
  <button id="btn5">요청</button>
  <br>
  응답 :
  <table id="result5" border="1">
    <tbody>
    요청5의 결과가 보여지는 영역
    </tbody>
  </table>

  <script>

  </script>



</body>
</html>