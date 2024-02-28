package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GbDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GbVo;


@WebServlet("/gbc")
public class GbController extends HttpServlet {
	//private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if("wform".equals(action)) {//등록폼 ++ 리스트 표시
			GbDao gd = new GbDao();
			
			List<GbVo> gList = gd.gblist();
			
			request.setAttribute("gList", gList);//리스트 표시
			
			WebUtil.forward("/addList.jsp", request, response);
			
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/addList.jsp");//등록폼 이동
			rd.forward(request, response);
			*/
		}else if("insert".equals(action)) {//등록
			String name = request.getParameter("name");
			String pw = request.getParameter("password");
			String content = request.getParameter("content"); 
			
			GbVo gv=new GbVo(name, pw, content);
			
			GbDao gd = new GbDao();
			
			gd.gbinsert(gv);
			
			WebUtil.redir("http://localhost:8080/guestBook3/gbc?action=wform", request, response);
			/*
			response.sendRedirect("http://localhost:8080/guestBook3/gbc?action=wform");
			*/
		}else if("delete".equals(action)) {//삭제폼
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			request.setAttribute("no", no);
			
			WebUtil.forward("/deleteForm.jsp", request, response);
			/*
			RequestDispatcher rd = request.getRequestDispatcher("/deleteForm.jsp");
			rd.forward(request, response);
			*/
		}else if("delete2".equals(action)) {//삭제
			
			String pw = request.getParameter("password");
			int no = Integer.parseInt(request.getParameter("no"));
			
			GbDao gd = new GbDao();
			
			gd.gbdelete(no, pw);
			
			WebUtil.redir("http://localhost:8080/guestBook3/gbc?action=wform", request, response);
			/*
			response.sendRedirect("http://localhost:8080/guestBook3/gbc?action=wform");
			*/	
		}
	}


	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
	}

}
