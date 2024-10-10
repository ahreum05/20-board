package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardModifyFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		int pg = Integer.parseInt(request.getParameter("pg"));

		BoardDAO dao = new BoardDAO();
		BoardBean dto = dao.boardDetail(board_num);

		// 2. 데이터 공유
		request.setAttribute("dto", dto);
		request.setAttribute("pg", pg);

		// 3. view 처리 파일명 리턴
		return "/board/boardModify.jsp";
	}

}
