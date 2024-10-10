package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import board.bean.BoardBean;

public class BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;   // 커넥션풀을 관리하는 클래스
	
	public BoardDAO() {
		Context context;
		try {
			context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}		
	}
	
	// db 접속 끊기
	public void close() {
		try {
			if (rs != null)	rs.close();
			if (pstmt != null) pstmt.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 저장하기
	public int boardWrite(BoardBean bean) {
		int result = 0;
		String sql = "insert into board2 values "
		   		   + " (seq_num.nextval, ?, ?, ?, ?, ?, "
		   		   + " seq_num.currval, 0, 0, 0, sysdate)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBoard_name());
	        pstmt.setString(2, bean.getBoard_pass());
	        pstmt.setString(3, bean.getBoard_subject());
	        pstmt.setString(4, bean.getBoard_content());
	        pstmt.setString(5, bean.getBoard_file());
	        
	        result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	// 목록보기
	// => 답글을 포함시켜서 보기
	public List<BoardBean> boardList(int startNum, int endNum) {
		List<BoardBean> list = new ArrayList<BoardBean>();
		String sql = "select *  from " 
				   + " (select rownum rn, tt.* from "
				   + " (select * from board2 order by board_re_ref desc, board_re_seq asc) tt) " 
				   + " where rn>=? and rn<=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardBean dto = new BoardBean();
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_name(rs.getString("board_name"));
				dto.setBoard_pass(rs.getString("board_pass"));
				dto.setBoard_subject(rs.getString("board_subject"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_re_ref(rs.getInt("board_re_ref"));
				dto.setBoard_re_lev(rs.getInt("board_re_lev"));
				dto.setBoard_re_seq(rs.getInt("board_re_seq"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));
				// 년월일시분초 데이터에서 년월일만 저장
				String date = rs.getString("board_date").split(" ")[0];				
				dto.setBoard_date(date);
				//System.out.println(dto.toString());
				// 리스트에 저장
				list.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	// 총 데이터 갯수
	public int getTotalA() {
		int totalA = 0;
		String sql = "select count(*) as cnt from board2";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalA = rs.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return totalA;
	}
	// 상세보기
	public BoardBean boardDetail(int board_num) {
		BoardBean dto = null;
		String sql = "select * from board2 where board_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, board_num);
	        rs = pstmt.executeQuery();
	        
	        if(rs.next()) {
				dto = new BoardBean();
				dto.setBoard_num(rs.getInt("board_num"));
				dto.setBoard_name(rs.getString("board_name"));
				dto.setBoard_pass(rs.getString("board_pass"));
				dto.setBoard_subject(rs.getString("board_subject"));
				dto.setBoard_content(rs.getString("board_content"));
				dto.setBoard_file(rs.getString("board_file"));
				dto.setBoard_re_ref(rs.getInt("board_re_ref"));
				dto.setBoard_re_lev(rs.getInt("board_re_lev"));
				dto.setBoard_re_seq(rs.getInt("board_re_seq"));
				dto.setBoard_readcount(rs.getInt("board_readcount"));
				// 년월일시분초 데이터에서 년월일만 저장
				String date = rs.getString("board_date").split(" ")[0];				
				dto.setBoard_date(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return dto;
	}
	
	// 조회수 증가
	public int upReadcount(int board_num) {
		int result = 0;
		String sql = "update board2 set board_readcount=board_readcount+1 "
				   + "where board_num =?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, board_num); 
	        result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	// 삭제하기
	public int deleteBoard(int board_num) {
		int result = 0;
		String sql = "delete board2 where board_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	// 수정하기
	public int boardModify(BoardBean bean) {
		int result = 0;
		String sql = "update board2 set board_name=?, board_pass=?, "
				   + " board_subject=?, board_content=? "
				   + "	where board_num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBoard_name());
			pstmt.setString(2, bean.getBoard_pass());
			pstmt.setString(3, bean.getBoard_subject());
			pstmt.setString(4, bean.getBoard_content());
			pstmt.setInt(5, bean.getBoard_num());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	// 답글 저장
	public int insertReply(BoardBean bean) {
		int result = 0;
		String sql = "";
		// 원본글 데이터
		int re_ref = bean.getBoard_re_ref();
		int re_lev = bean.getBoard_re_lev();
		int re_seq = bean.getBoard_re_seq();
		
		try {
			conn = ds.getConnection();
			// 기존 답글의 등록순서를 재정리
			// => 같은 그룹의 원글 re_seq보다 큰 답글의 re_seq를 1씩 증가
			sql = "update board2 set board_re_seq=board_re_seq+1 "
				+ " where board_re_ref=? and board_re_seq>?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			pstmt.execute(); // 리턴값 사용안함
			// 추가되는 답글의 re_ref는 원글의 re_ref를 저장
			// 추가되는 답글의 re_lev는 원글 re_lev에 1증가된 값 저장
			// 추가되는 답글의 re_seq는 원글 re_seq에 1증가된 값 저장
			re_lev += 1;
			re_seq += 1;
			sql = "insert into board2 values "
				+ " (seq_num.nextval, ?, ?, ?, ?,"
				+ " ?, ?, ?, ?, 0, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getBoard_name());
			pstmt.setString(2, bean.getBoard_pass());
			pstmt.setString(3, bean.getBoard_subject());
			pstmt.setString(4, bean.getBoard_content());
			pstmt.setString(5, " ");  // 답글에는 파일을 업로드하지 않음
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}




