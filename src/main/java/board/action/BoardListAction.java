package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.BoardBean;
import board.dao.BoardDAO;

public class BoardListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 데이터 처리
		int pg = 1;
		if(request.getParameter("pg") != null && !request.getParameter("pg").equals("")) {
			pg = Integer.parseInt(request.getParameter("pg"));
		}
		// 목록 10개
		int endNum = pg * 10;
		int startNum = endNum - 9;

		BoardDAO dao = new BoardDAO();
		List<BoardBean> list = dao.boardList(startNum, endNum);
		
		// 페이징 : 3블럭
		// 총 페이지 계산식 : (총 글 수 + 9) / 10
		int totalA = dao.getTotalA(); 	// 총 데이터 개수
		int totalP = (totalA+9)/10;		// 총 페이지 수

		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;

		// 2. 데이터 공유
		request.setAttribute("list", list);
		request.setAttribute("pg", pg);		
		request.setAttribute("totalP", totalP);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		// 3. view 처리 파일명 리턴
		return "/board/boardList.jsp";
	}

}
