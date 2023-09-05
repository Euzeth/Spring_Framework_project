<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
		<%@include file="/resources/static/css/common.css" %>
		<%@include file="/resources/static/css/header.css" %>
		<%@include file="/resources/static/css/Login_Page.css" %>
		<%@include file="/resources/static/css/footer.css" %>		
	</style>
</head>
<body>
	<div class="wrapper">
        <!-- header -->
            <!-- top-header -->
            <header>
                <div class="log-header">
                    <a href=<c:url value="/index" />><img class="logo" src="${pageContext.request.contextPath}/resources/static/img/로고.png" alt=""></a>
                    <ul>
                        <li><a href=""><img  src="${pageContext.request.contextPath}/resources/static/img/english.png" alt="">ENGLISH</a></li>
                    </ul>
                </div>
                <div class="logheader_line"></div>
            </header>
            
            <section class="login">
                <h1>워터멜론에서 즐거운 음악감상하세요!</h1>
                <form action="${pageContext.request.contextPath}/member/login" method="post">
                <ul>
                    <li><input name="username" type="text" placeholder="아이디" title="아이디입력"></li>
                    <li><input name="password" type="password" placeholder="비밀번호" title="비밀번호입력"></li>
                    <div class="log_chk">
                        <li><input type="checkbox" id="chk_id"><label for="chk_id">아이디저장</label></li>
                        <li><input type="checkbox" id="auto"><label for="auto">자동로그인</label></li>
                    </div>
                    <li><button type="submit" id="login">로그인</button></li>
                </ul>
                </form>
                <div>
                    <ul>
                        <li><a href="">아이디 찾기</a></li>
                        <li><a href="">비밀번호 찾기</a></li>
                        <li><a href=<c:url value="/member/join" />>회원가입</a></li>
                    </ul>
                </div>
            </section>

            <footer>
                <div class="footer_line"></div>
                <div>
                    <ul class="footer_menu">
                        <li><a href="">이용약관</a></li>
                        <li><a href="">고객센터</a></li>
                        <li><a href="">제휴/프로모션</a></li>
                        <li><a href=""> 개인정보처리방침</a></li>
                    </ul>
                </div>
                <p></p>
                <div class="Site_info">
                    <img id="footer_logo" src="${pageContext.request.contextPath}/resources/static/img/로고.png" alt="">
                    <span class="Sitename">(주)WATERMELON MUSIC</span>
                    <span>대구광역시 중구 중앙대로 366 반월센트럴타워 10층</span>
                    <span>대표이사 : 윤치연, 김예솔, 이헌지, 최정기</span>
                    <span>사업자등록번호 : 024-04-02023</span>
                    <br>
                    <span>문의전화 : 1544-1111 (평일 09:00-18:00)</span>
                    <span>WATERMELON MUSIC으로 음악과 함께하는 즐거운 하루 보내세요!</span>
                </div>
                
            </footer>
    </div>
    
    <script
			src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js"
			integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ=="
			crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    
    <!-- <script>

	const login = document.getElementById("login");
	login.addEventListener('click', function(evnet){
		event.preventDefault();
		const form = document.querySelector('form');
		
		axios.post(form.action)
		.then(response=>{
			window.location.href = response.data.redirectUrl;
			console.log("success!");
		})
		.catch(error=>{
			console.log("error!");
		})
	})
    </script> -->
    
</body>
</html>