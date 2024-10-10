<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h2 {
	text-align: center;
}
table {
	width: 450px;
	margin: auto;
}
#tr_top {
	background: orange;
	text-align: center;
}
.currentPaging {color: red; text-decoration: underline;}
.paging {color: blue;  text-decoration: none;}
</style>
</head>
<body>
	<h2>글 목록</h2>
	<table>
		<tr id="tr_top">
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		
		<c:forEach var="dto" items="${list}">
			<tr align="center">
				<td>${dto.getBoard_num()}</td>
				<td align="left" width="180">
					<%-- board_re_lev * 2 만큼 들여쓰기 --%>
					<c:if test="${dto.board_re_lev != 0}">
						<c:forEach var="i" begin="0" end="${dto.board_re_lev}">
							&nbsp;&nbsp;
						</c:forEach>
						<%-- 한글 ㅁ 을 치고, 한자키를 누르면 특수키 창이 열림 --%>
						▶
					</c:if>
					
					<a href="boardDetail.do?board_num=${dto.board_num}&pg=${pg}">${dto.getBoard_subject()}</a>
				</td>
				<td>${dto.getBoard_name()}</td>
				<td>${dto.getBoard_date()}</td>
				<td>${dto.getBoard_readcount()}</td>
			</tr>
		</c:forEach>
		
		<tr>
			<td colspan="5" align="center">
			<c:if test="${startPage > 3}">               
               [<a href="boardList.do?pg=${startPage -1}" 
               	   class="paging">이전</a>]
            </c:if> 
            
            <c:forEach var="i" begin="${startPage}" end="${endPage}">
               <c:if test="${pg == i}">
                  [<a href="boardList.do?pg=${i}"   
                  	  class="currentPaging">${i}</a>]
               </c:if>
               <c:if test="${pg != i}">
                  [<a href="boardList.do?pg=${i}" 
                      class="paging">${i}</a>]
               </c:if>   
            </c:forEach>
            
            <c:if test="${endPage < totalP}">
               [<a href="boardList.do?pg=${endPage+1}"   
               	   class="paging">다음</a>]               
            </c:if>
				
			</td>
		</tr>
		
		<tr>
			<td colspan="5">
				<a href="/20-board/index.jsp">메인 화면</a>
			</td>
		</tr>		
	</table>
	
</body>
</html>