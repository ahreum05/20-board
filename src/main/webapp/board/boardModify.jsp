<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
form {
	width: 500px;
	height: 370px;
	border: 1px solid lightgray;
	margin: auto;
}
h2 {text-align: center; }
table {
	margin: auto;
	width: 450px;
}
.td_left {
	width: 150px;
	background: orange;
	text-align: center;
}
.td_right {
	width: 300px;
	background: skyblue;
}
</style>
<script type="text/javascript">
function inputCheck(){
	var frm = document.boardform;
	
	if (!frm.board_name.value.trim()) {
		alert("이름 입력하세요.");
		frm.board_name.value = "";
		frm.board_name.focus();
		return false;
	}
	
	if (!frm.board_pass.value.trim()) {
		alert("비밀번호를 입력하세요.");
		frm.board_pass.value = "";
		frm.board_pass.focus();
		return false;
	}
	
	if (!frm.board_subject.value.trim()) {
		alert("제목을 입력하세요.");
		frm.board_subject.value = "";
		frm.board_subject.focus();
		return false;
	}
	
	if (!frm.board_content.value.trim()) {
		alert("내용을 입력하세요.");
		frm.board_content.value = "";
		frm.board_content.focus();
		return false;
	}	
	frm.submit();
}
</script>
</head>
<body>
	<h2>게시판 글수정</h2>
	<form action="boardModifyPro.do" method="post" name="boardform">
		<input type="hidden" name="pg" value="${pg}">
		<input type="hidden" name="board_num" value="${dto.board_num}">
		<input type="hidden" name="board_pass_origin" 
							 value="${dto.board_pass}">
		<table>
			<tr>
				<td class="td_left"><label>글쓴이</label></td>
				<td class="td_right">
					<input type="text" name="board_name"
					value="${dto.board_name}" required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>비밀번호</label></td>
				<td class="td_right">
					<input type="password" name="board_pass"
					required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>제 목</label></td>
				<td class="td_right">
					<input type="text" name="board_subject"
					value="${dto.board_subject}" required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>내 용</label></td>
				<td class="td_right">
					<textarea rows="15" cols="40" name="board_content" 
						required="required">${dto.board_content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<a href="#" onclick="inputCheck()">[수정]</a>
					<a href="#" onclick="history.back()">[뒤로]</a>
				</td>
			</tr>	
		</table>	
	</form>
</body>
</html>