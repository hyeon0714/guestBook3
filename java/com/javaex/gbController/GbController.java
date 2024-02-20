package com.javaex.gbController;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.gbDao.GbDao;
import com.javaex.gbVo.GbVo;


@WebServlet("/gbc")
public class GbController extends HttpServlet {
	//private static final long serialVersionUID = 1L;
       
    int no = -1;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if("wform".equals(action)) {//등록폼 ++ 리스트 표시
			GbDao gd = new GbDao();
			
			List<GbVo> gList = gd.gblist();
			
			request.setAttribute("gList", gList);//리스트 표시
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");//등록폼 이동
			rd.forward(request, response);
			
		}else if("insert".equals(action)) {//등록
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String content = request.getParameter("content"); 
			
			GbVo gv=new GbVo(name, pw, content);
			
			GbDao gd = new GbDao();
			
			gd.gbinsert(gv);
			
			response.sendRedirect("http://localhost:8080/guestBook3/gbc?action=wform");
			
		}else if("delete".equals(action)) {//삭제폼
			
			no = Integer.parseInt(request.getParameter("no"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);
			
		}else if("delete2".equals(action)) {//삭제
			
			String pw = request.getParameter("password");
			
			GbDao gd = new GbDao();
			
			gd.gbdelete(no, pw);
			
			response.sendRedirect("http://localhost:8080/guestBook3/gbc?action=wform");	
		}
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
