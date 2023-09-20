
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MEMBERSHIP_MEMBER PAGE</title>
<style>
<%@ include file="/resources/static/css/common.css" %>
<%@ include file="/resources/static/css/header.css" %>
<%@ include file="/resources/static/css/membershipM_Page.css" %>
<%@ include file="/resources/static/css/footer.css" %>
</style>

</head>
<body>
	<div class="wrapper">
		<header>
			<div class="log-header">
				<a href=<c:url value="/indexlog" />><img class="logo"
					src="${pageContext.request.contextPath}/resources/static/img/로고.png"
					alt=""></a>
				<ul>
					<li><a href=""><img
							src="${pageContext.request.contextPath}/resources/static/img/english.png"
							alt="">ENGLISH</a></li>
				</ul>
			</div>
			<div class="logheader_line"></div>
		</header>
		<section class="membership_M">
			<h1>멤버십 관리 페이지입니다.</h1>
			<ul>
				<li><span class="title">멤버십회원 전체조회
						<button class="alluser" id="showButton">조회하기</button>
				</span>
				<hr /></li>
				<div class="selectall">
					<table>
						<tr>
							<td class="search_title1" style="color: rgb(92, 115, 6);">CODE</td>
							<td class="search_title1" style="color: rgb(92, 115, 6);">ID</td>
							<td class="search_title1" style="color: rgb(92, 115, 6);">NAME</td>
							<td class="search_title2" style="color: rgb(92, 115, 6);">START_DATE</td>
							<td class="search_title2" style="color: rgb(92, 115, 6);">END_DATE</td>
						</tr>
						<c:forEach items="${list}" var="dto">
							<tr>
								<td class="search_title1">${dto.membershipCode}</td>
								<td class="search_title1">${dto.id}</td>
								<td class="search_title1">${dto.name}</td>
								<td class="search_title2">${dto.startDate}</td>
								<td class="search_title2">${dto.endDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
				<li><span class="title">멤버십 키워드검색</span>
				<hr /></li>
				<div class="search_block">
					<select id="select">
						<option value="id" selected>ID</option>
						<option value="membershipCode">멤버십 코드</option>
						<option value="endDate">종료 날짜</option>
					</select>
					<form
						action="${pageContext.request.contextPath}/membership_selectId" method="get">
						<input type="text" class="search_input"
							placeholder="검색할 ID를 입력하세요" name="id">
						<button class="ms_search" type="submit" id="key_search">검색</button>
					</form>
				</div>
				<div class="keywordShow">
					<table>
						<tr>
							<td class="search_title1" style="color: rgb(92, 115, 6);">CODE</td>
							<td class="search_title1" style="color: rgb(92, 115, 6);">ID</td>
							<td class="search_title1" style="color: rgb(92, 115, 6);">NAME</td>
							<td class="search_title2" style="color: rgb(92, 115, 6);">START_DATE</td>
							<td class="search_title2" style="color: rgb(92, 115, 6);">END_DATE</td>
						</tr>
						<c:if test="${not empty selectedId}">
							<tr>
								<td class="search_title1">${selectedId.membershipCode}</td>
								<td class="search_title1">${selectedId.id}</td>
								<td class="search_title1">${selectedId.name}</td>
								<td class="search_title2">${selectedId.startDate}</td>
								<td class="search_title2">${selectedId.endDate}</td>
							</tr>
						</c:if>
						<c:forEach items="${selectedCodeList}" var="dto">
							<tr>
								<td class="search_title1">${dto.membershipCode}</td>
								<td class="search_title1">${dto.id}</td>
								<td class="search_title1">${dto.name}</td>
								<td class="search_title2">${dto.startDate}</td>
								<td class="search_title2">${dto.endDate}</td>
							</tr>
						</c:forEach>
						<c:forEach items="${selectedDateList}" var="dto">
							<tr>
								<td class="search_title1">${dto.membershipCode}</td>
								<td class="search_title1">${dto.id}</td>
								<td class="search_title1">${dto.name}</td>
								<td class="search_title2">${dto.startDate}</td>
								<td class="search_title2">${dto.endDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br />
				<li><span class="title">회원 삭제</span>
				<hr /></li>
				<form action="${pageContext.request.contextPath}/membership_delete" method="post">
					<input type="text" id="remove_input" name="id"
						placeholder="멤버십에서 삭제할 ID를 입력하세요">
					<button class="ms_delete">삭제</button>
				</form>
				<br />
			</ul>

		</section>
		<footer>
			<div class="footer_line"></div>
			<div>
				<ul class="footer_menu">
					<li><a href="">이용약관</a></li>
					<li><a href="">고객센터</a></li>
					<li><a href="">제휴/프로모션</a></li>
					<li style="width: 170px"><a href="">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개인정보처리방침</a></li>
				</ul>
			</div>
			<p></p>
			<div class="Site_info">
				<img id="footer_logo"
					src="${pageContext.request.contextPath}/resources/static/img/로고.png"
					alt=""> <span class="Sitename">(주)WATERMELON MUSIC</span> <span>대구광역시
					중구 중앙대로 366 반월센트럴타워 10층</span> <span>대표이사 : 윤치연, 김예솔, 이헌지, 최정기</span> <span>사업자등록번호
					: 024-04-02023</span> <br> <span>문의전화 : 1544-1111 (평일
					09:00-18:00)</span> <span>WATERMELON MUSIC으로 음악과 함께하는 즐거운 하루 보내세요!</span>
			</div>
		</footer>
	</div>


	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/axios/1.4.0/axios.min.js"
		integrity="sha512-uMtXmF28A2Ab/JJO2t/vYhlaa/3ahUOgj1Zf27M5rOo8/+fcTUVH0/E0ll68njmjrLqOBjXM3V9NiPFL5ywWPQ=="
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>


	<script>
	//회원 전체조회 script
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
	  
	//회원 단건조회 script
	 		//단건조회 관련 변수
	       var select = document.getElementById('select');
		   var searchBlock = document.querySelector('.search_block');
		   // 옵션 선택이 변경될 때 이벤트 리스너 추가
		  select.addEventListener('change', function() {
		  var selectedOption = select.value;
		  var formAction = '';
		  var inputName = '';
		  var inputPlaceholder = '';
		
		  // 선택된 옵션에 따라서 form action, input name, placeholder 설정
		  if (selectedOption === 'id') {
		  	formAction = '${pageContext.request.contextPath}/membership_selectId';
		    inputName = 'id';
			inputPlaceholder = '검색할 ID를 입력하세요';
		  } else if (selectedOption === 'membershipCode') {
			    formAction = '${pageContext.request.contextPath}/membership_selectCode';
			    inputName = 'membershipCode';
			    inputPlaceholder = '멤버십 코드를 입력하세요';
		  } else if (selectedOption === 'endDate') {
	      	    formAction = '${pageContext.request.contextPath}/membership_selectDate';
			    inputName = 'endDate';
			    inputPlaceholder = '검색할 종료 날짜를 입력하세요';
		  }
		
		        // 폼과 입력 필드 업데이트
		        var form = searchBlock.querySelector('form');
		        form.action = formAction;
		        var input = form.querySelector('.search_input');
		        input.name = inputName;
		        input.placeholder = inputPlaceholder;
		    	});
		
	
		// 버튼 클릭 이벤트 핸들러
		  var key_search = document.getElementById('key_search');
		  var keywordShowDiv = document.querySelector('.keywordShow');

		  // key_search 버튼 클릭 이벤트 핸들러
		  key_search.addEventListener('click', function () {
		      // 현재 상태를 확인하여 토글
		      if (keywordShowDiv.style.display === 'none' || keywordShowDiv.style.display === '') {
		          keywordShowDiv.style.display = 'block'; // 보이도록 함
		      } else {
		          keywordShowDiv.style.display = 'none'; // 숨김
		      }
		  });
	
	//회원 삭제 script
       const form = document.querySelector('.ms_delete');
       const searchInput = document.querySelector('#remove_input'); 

       form.addEventListener('click', async function (event) {
           event.preventDefault();

           const searchValue = searchInput.value;

           try {
               const response = await axios.post('/app/membership_delete', null, {
                   params: { id: searchValue }
               });
               console.log('서버 응답:', response);
               alert('삭제가 완료되었습니다.');
               location.reload();
           } catch (error) {
               if (error.response) {
                   console.error('서버 응답 오류:', error.response.data);
               } else {
                   console.error('요청 오류:', error.message);
               }
               // 오류가 발생했을 때 처리
               alert('삭제에 실패했습니다.');
           }
       });
	});
	
	
	</script>

</body>
</html>