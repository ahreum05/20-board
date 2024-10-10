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
h2 {text-align: center;}
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
</head>
<body>
	<h2>게시판 글등록</h2>
	<form action="boardWritePro.do" method="post" name="boardform"
		  enctype="multipart/form-data">
		<table>
			<tr>
				<td class="td_left"><label>글쓴이</label></td>
				<td class="td_right"><input type="text" name="board_name" 
						required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>비밀번호</label></td>
				<td class="td_right"><input type="password" name="board_pass" 
						required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>제 목</label></td>
				<td class="td_right"><input type="text" name="board_subject" 
						required="required"></td>
			</tr>
			<tr>
				<td class="td_left"><label>내 용</label></td>
				<td class="td_right"><textarea rows="15" cols="40"
					name="board_content" required="required"></textarea></td>
			</tr>
			<tr>
				<td class="td_left"><label>파일첨부</label></td>
				<td class="td_right"><input type="file" name="board_file" 
						required="required"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록">&nbsp;&nbsp;
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>