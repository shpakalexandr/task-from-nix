package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			System.out.println("going to forward");
			RequestDispatcher dispatcher = req
					.getRequestDispatcher("/WEB-INF/jsps/MainMenu.jsp");
			dispatcher.forward(req, resp);	

	}
}