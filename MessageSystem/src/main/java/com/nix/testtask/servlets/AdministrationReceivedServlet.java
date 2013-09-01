package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.Message;
import com.nix.testtask.db.User;

public class AdministrationReceivedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to administrationreceived-page");

		 System.out.println("UserName = " + (String)req.getSession().getAttribute("UserName"));
		
		if (req.getSession().getAttribute("UserName") == null) {

			int id = Integer.parseInt(req.getParameter("id"));

			User u = DatabaseHelper.getUserByID(id);
			List<Message> mesList = DatabaseHelper.selectUserMessage(
					"received", u.getUsernickname());
			req.getSession().setAttribute("UserName", u.getUsernickname());
			req.setAttribute("username", u.getUsernickname());
			req.setAttribute("mesList", mesList);
		} else {
			String UserName = (String) req.getSession().getAttribute("UserName");
			List<Message> mesList = DatabaseHelper.selectUserMessage("received", UserName);
			req.setAttribute("username", UserName);
			req.setAttribute("mesList", mesList);
//			req.getSession().setAttribute("UserName", null);
		}

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/AdministrationReceived.jsp");
		dispatcher.forward(req, resp);
	}
}
