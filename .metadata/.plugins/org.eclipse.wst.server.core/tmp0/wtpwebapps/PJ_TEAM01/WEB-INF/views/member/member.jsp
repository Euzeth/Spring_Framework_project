<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBER PAGE</title>
<style>
		<%@include file="/resources/static/css/common.css" %>
		<%@include file="/resources/static/css/header.css" %>
		<%@include file="/resources/static/css/member_Page.css" %>
		<%@include file="/resources/static/css/footer.css" %>		
</style>
</head>
<body>
<div class="wrapper">
<header>
   <div class="log-header">
       <a href=<c:url value="/indexlog" />><img class="logo" src="${pageContext.request.contextPath}/resources/static/img/로고.png" alt=""></a>
       <ul>
          <li><a href=""><img  src="${pageContext.request.contextPath}/resources/static/img/english.png" alt="">ENGLISH</a></li>
       </ul>
    </div>
    <div class="logheader_line"></div>
</header>
<section class="member">

<div>
	<hr/>
	<a href="javascript:history.go(-1)">이전으로</a>
	
	<form action="${pageContext.request.contextPath}/member/remove/{id}" method="post">
    	<input type="text" name="id" placeholder="사용자 검색">
    	<button type="submit" >삭제</button>
	</form>
</div>


    <button class="alluser" id="showButton">모든회원검색</button>
    <div class="selectall">
    	<c:forEach items="${list}" var="dto">
			<span>${dto.id}</span>&nbsp;&nbsp;<span>${dto.name}</span>
			&nbsp;&nbsp;<span>${dto.addr}</span>&nbsp;&nbsp;<span>${dto.phone}</span><br/>
		</c:forEach>
    </div>
    <hr />

	
    <form action="member/search" method="post">
    <div class="search_block" style= display="none">
    <input type="text" name="id" placeholder="검색할 id를 입력하세요">
    <button class="oneuser" type="submit" >검색</button>
    </div>
    <div class="oneshowBlock"></div>
    <hr />
	</form> 

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


<script src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js" integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	/* const search_btn_el=document.querySelector('.oneuser');
	search_btn_el.addEventListener('click',function(){
	const projectPath = '${pageContext.request.contextPath}';
	console.log("search_btn_el click",projectPath);
	
	//axios
	axios.post("http://localhost:9090" + projectPath + "/member/search.do", {
	    id: document.querySelector('input[name="id"]').value 
	  })
	    .then(response => {
	      console.log("success!", response.data);
	      const show_block_el = document.querySelector('.oneshowBlock');
	      
	      const list = response.data;

	      list.forEach((dto) => {
	        console.log('dto', dto);
	        const dto_el = document.createElement('div');
	        dto_el.classList.add("item");

	        dto_el.innerHTML += "<span>" + dto.id + "</span>";
	        dto_el.innerHTML += "<span>" + dto.role + "</span>";
	        dto_el.innerHTML += "<span>" + dto.name + "</span>";
	        dto_el.innerHTML += "<span>" + dto.addr + "</span>";
	        dto_el.innerHTML += "<span>" + dto.phone + "</span><br/>";
	        show_block_el.appendChild(dto_el); 
	      })
	    })
	    .catch(error => { console.log("fail.."); })
	});  */ 
	
	</script>
	
	<script>
	
	document.addEventListener('DOMContentLoaded', function() {
	    // 버튼 클릭 이벤트 핸들러
	    var showButton = document.getElementById('showButton');
	    var selectAllDiv = document.querySelector('.selectall');
	    
	    showButton.addEventListener('click', function() {
	        // 현재 상태를 확인하여 토글
	        if (selectAllDiv.style.display === 'none' || selectAllDiv.style.display === '') {
	            selectAllDiv.style.display = 'block'; // 보이도록 함
	        } else {
	            selectAllDiv.style.display = 'none'; // 숨김
	        }
	    });
	});
	
	</script>

</body>
</html>