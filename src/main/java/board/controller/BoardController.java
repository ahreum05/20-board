package board.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.action.Action;
import board.action.BoardDeleteFormAction;
import board.action.BoardDeleteProAction;
import board.action.BoardDetailAction;
import board.action.BoardListAction;
import board.action.BoardModifyFormAction;
import board.action.BoardModifyProAction;
import board.action.BoardReplyFormAction;
import board.action.BoardReplyProAction;
import board.action.BoardWriteFormAction;
import board.action.BoardWriteProAction;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();
   
    public BoardController() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
    	// 1. command.properties 파일 읽기
    	String realFolder = config.getServletContext().getRealPath("property");
        String realPath = realFolder + "/" + "command.properties";

        FileInputStream fis = null;
        Properties properties = new Properties();
        
        try { 
            fis = new FileInputStream(realPath);
            properties.load(fis);
         } catch (FileNotFoundException e) {
            e.printStackTrace();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            try {
               if(fis != null) fis.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }


    	// 2. Map에 (요청정보, model 객체)로 저장하기
        Iterator<?> iterator = properties.keySet().iterator();
        
        while(iterator.hasNext()) {
        	String command = (String) iterator.next();
            String className = properties.getProperty(command);
            
            try {
				Class<?> commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance(); // 객체 생성
				map.put(command, commandInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
        }
        System.out.println(map);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		// 1. 요청 정보 확인
		// http://localhost:8080/19-board/boardWriteForm.do
		// url에서 "19-board" 뒤의 "/boardWriteForm.do" 만 추출
		String command = request.getServletPath();
		System.out.println("command = " + command);
		
		// 2. 요청 작업 처리 (= Model 선택)
		Action action = (Action) map.get(command);		
		
		// 3. 데이터 처리 + view 처리 파일 선택
		String view = null;
		if(action != null) {
			try {
				view = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 4. 페이지 이동
		if(view != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}
}
