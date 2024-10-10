package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardModifyProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int pg = Integer.parseInt(request.getParameter("pg"));
		String board_pass_origin = request.getParameter("board_pass_origin");
		String board_name = request.getParameter("board_name");
		String board_pass = request.getParameter("board_pass");
		String board_subject = request.getParameter("board_subject");
		String board_content = request.getParameter("board_content");
		// 글쓴이 인지 확인
		int result = 0;
		if(board_pass_origin.equals(board_pass)) {
			BoardBean bean = new BoardBean();
			bean.setBoard_num(board_num);
			bean.setBoard_name(board_name);
			bean.setBoard_pass(board_pass);
			bean.setBoard_subject(board_subject);
			bean.setBoard_content(board_content);

			BoardDAO dao = new BoardDAO();
		    result = dao.boardModify(bean);
		}
		// 2. 데이터 공유
		if(result == 0) {  // 저장 실패
	    	response.setContentType("text/html; charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println("alert('수정 실패');");
	    	String href = "location.href='boardDetail.do?pg=" + pg 
    				+ "&board_num=" + board_num + "'"; 
	    	out.println(href);
	    	out.println("</script>");
	    	return null;
	    }		
		// 3. view 처리 파일명 리턴
		return "boardDetail.do?board_num=" + board_num + "&pg=" + pg;
	}

}
