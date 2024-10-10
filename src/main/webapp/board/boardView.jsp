<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#articleForm {
	width: 500px;
	height: 350px;
	border: 1px solid red;
	margin: auto;
}
#commandList {
	width: 500px;
	margin: auto;
	text-align: center;
}
h2 {
	text-align: center;
}
#info {
	height: 40px;
	text-align: center;
}
#content {
	background: orange;
	margin-top: 20px;
	height: 220px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="articleForm">
		<h2>글 내용 상세보기</h2>
		<p id="info">제목 : ${dto.board_subject}&nbsp;&nbsp;&nbsp; 
			첨부파일 : <a href="#">${dto.board_file}</a></p>
		<div id="content"><pre>${dto.board_content}</pre></div>		
	</div>	
	<div id="commandList">
		<a href="boardReplyForm.do?board_num=${dto.board_num}&pg=${pg}">[답변]</a>
		<a href="boardModifyForm.do?board_num=${dto.board_num}&pg=${pg}">[수정]</a>
		<a href="boardDeleteForm.do?board_num=${dto.board_num}&pg=${pg}">[삭제]</a>
		<a href="boardList.do?pg=${pg}">[목록]</a>
	</div>
</body>
</html>