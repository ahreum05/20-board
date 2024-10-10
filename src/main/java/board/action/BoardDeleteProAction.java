package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardDeleteProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		String board_pass = request.getParameter("board_pass");
		// 글쓴이인 지 확인
		BoardDAO dao = new BoardDAO();		
		BoardBean dto = dao.boardDetail(board_num); // 1줄 데이터 얻기
		
		int result = 0;		
		if(board_pass.equals(dto.getBoard_pass())) {
			// 글쓴이이면 삭제
			result = dao.deleteBoard(board_num);
		} 
		// 2. 데이터 공유
		if(result == 0) {  // 저장 실패
	    	response.setContentType("text/html; charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println("alert('삭제 실패');");
	    	String href = "location.href='boardDetail.do?pg=" + pg 
	    				+ "&board_num=" + board_num + "'"; 
	    	out.println(href);
	    	out.println("</script>");
	    	return null;
	    }
		
		// 3. view 처리 파일명 리턴
		return "boardList.do?pg=" + pg;
	}

}
