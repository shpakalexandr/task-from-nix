package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.User;

public class ModifyServlet extends HttpServlet{

	private static int id_Modify = 0;
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to modify-page");
		if (id_Modify == 0) {
			int id = Integer.parseInt(req.getParameter("id"));
			setIdModify(id);
		}
		
		System.out.println(getIdModify());
		
		User userInfo = DatabaseHelper.getUserByID(getIdModify());
		
		req.setAttribute("CREATE", false);
		req.setAttribute("userInfo", userInfo);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsps/CreateOrModifyUser.jsp");
		dispatcher.forward(req, resp);
	}
	public static int getIdModify() {
		return id_Modify;
	}
	public static void setIdModify(int id) {
		ModifyServlet.id_Modify = id;
	}

}
