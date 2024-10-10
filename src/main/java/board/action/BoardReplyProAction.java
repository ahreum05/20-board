package board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardReplyProAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int pg = Integer.parseInt(request.getParameter("pg"));
		BoardBean bean = new BoardBean();
		bean.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		bean.setBoard_name(request.getParameter("board_name"));
		bean.setBoard_pass(request.getParameter("board_pass"));
		bean.setBoard_subject(request.getParameter("board_subject"));
		bean.setBoard_content(request.getParameter("board_content"));
		bean.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		bean.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		bean.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		// db 
		BoardDAO dao = new BoardDAO();
		int result = dao.insertReply(bean);
		
		// 2. 데이터 공유
		if(result == 0) {  // 저장 실패
	    	response.setContentType("text/html; charset=UTF-8");
	    	PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println("alert('답글등록 실패');");
	    	out.println("history.back();");
	    	out.println("</script>");
	    	return null;
	    }
		// 3. view 처리 파일명 리턴
		// sendRedirect 방식 이동
		// 브라우저의 주소창의 url을 현재 url로 표시할 경우에 사용
		response.sendRedirect("boardList.do?pg=" + pg);
		return null;
		//return 
	}

}
