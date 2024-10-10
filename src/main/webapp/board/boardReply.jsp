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
	height: 390px;
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
	<h2>게시판 답글등록</h2>
	<form action="boardReplyPro.do" method="post" name="boardform">
		<input type="hidden" name="pg" value="${pg}">
		<input type="hidden" name="board_num" value="${article.board_num}">
		<input type="hidden" name="board_re_ref" value="${article.board_re_ref}">
		<input type="hidden" name="board_re_lev" value="${article.board_re_lev}">
		<input type="hidden" name="board_re_seq" value="${article.board_re_seq}">
		
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
				<td colspan="2" align="center">
					<input type="submit" value="답글등록">&nbsp;&nbsp;
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>	
	</form>
</body>
</html>