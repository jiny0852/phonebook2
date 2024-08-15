package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhonebookDao;
import com.javaex.vo.PersonVo;

//http://localhost:8080/pb/pbc
@WebServlet("/pbc") //("/PhonebookController")
public class PhonebookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// Controller 접수받는일 "업무구분"해주는 애
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//action 뭔지 알아야됨
		
		String action = request.getParameter("action");
		System.out.println("action : " + action);
		
		
		if ( "list".equals(action) ) { //null을 피하기 위해 반대로 쓰기 문자열에 리스트를 쓴것임
			
			//접수
			System.out.println("접수 성공 : list 요청");
			
			
			//db데이터 가져오기
			PhonebookDao phonebookDao = new PhonebookDao();
			List<PersonVo> personList = phonebookDao.getPersonList();
			//System.out.println(personList);
			
			
			//화면그리기 --> 포워드   html이있네?포워드 진행시켜
			//request 에 리스트 주소 넣기
			request.setAttribute("pList", personList);
			
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			
			
			
		} else if ( "writeform".equals(action) ) {
			
			System.out.println("등록 시작 : writeForm 요청"); // 업무보고   
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
			
		} else if ( "insert".equals(action) ) {
			
			System.out.println("등록 요청 데이터 3개 저장해줘"); // 업무보고
			
			PersonVo personVo = new PersonVo();
		
			personVo.setName(request.getParameter("name"));
			personVo.setHp(request.getParameter("hp"));
			personVo.setCompany(request.getParameter("company"));
			
			System.out.println("controller" + personVo);
			
			/*
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			
			PersonVo personVo = new PersonVo();
			personVo.setName(name);
			personVo.setHp(hp);
			personVo.setCompany(company);
			
			PersonVo personVo = new PersonVo(name, hp, company); //생성자 새로 만듦
			
			System.out.println(personVo);
			*/
			
			
			//dao를 메모리에 올린다
			//insertPerson(personVo) 사용해서 db에 저장한다
			PhonebookDao phonebookDao = new PhonebookDao();
			phonebookDao.insertPerson(personVo);
			
			//리다이렉트
			
			
			//response.sendRedirect("/pb2/pbc?action=list");
			
			
			/*response.sendRedirect("http://localhost:8080/pb2/pbc?action=list");*/
			
			
			
			/* 안내메세지로 대체 re_direct+enter 키 로 자동로 보내버리기
			
			//getPersonList 를 사용해서 전제 리스트를 가져온다
			List<PersonVo> personList = phonebookDao.getPersonList();
			
			//화면그리기 --> 포워드
			//request 에 리스트 주소 넣기
			request.setAttribute("pList", personList);
			
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/list.jsp");
			rd.forward(request, response);
			*/
			
		} else if ( "editform".equals(action) ) {
			
			System.out.println("정보 수정 !!! 폼 !!! 시작");
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("no : " + no);
			
			//Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//getPersonOne(no) 로 1명의 데이터 주소를 가져온다
			PersonVo personVo = phonebookDao.getPersonOne(no);
			
			System.out.println("Con - getPersonOne : " + personVo);
			
			//화면+데이터 수정폼
			//리퀘스트 어트리부트 영역에 personVo 주소를 담는다
			request.setAttribute("personVo", personVo);
			
			//포워드 editform.jsp
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
			rd.forward(request, response);
			
			
		} else if ( "update".equals(action) ) {
			
			System.out.println("수정");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String hp = request.getParameter("hp");
			String company = request.getParameter("company");
			
			PersonVo personVo = new PersonVo(no, name, hp, company);
			System.out.println(personVo);
			
			//Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//phonebookDao를 통해서 수정update를 시킨다
			phonebookDao.updatePerson(personVo);
			
			//리다이렉트 시킨다
			//response.sendRedirect("/pb2/pbc?action=editForm");
			response.sendRedirect("/pb2/pbc?action=list");
			
			
			
		} else if ( "delete".equals(action) ) {
			
			System.out.println("수정");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			
			//Dao를 메모리에 올린다
			PhonebookDao phonebookDao = new PhonebookDao();
			
			//phonebookDao를 통해서 삭제delete를 시킨다
			phonebookDao.deletePerson(no);
			
			//리다이렉트 시킨다
			response.sendRedirect("/pb2/pbc?action=list");
			
			
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
