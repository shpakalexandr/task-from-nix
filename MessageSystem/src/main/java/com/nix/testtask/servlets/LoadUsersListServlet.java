package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.User;

public class LoadUsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("going to list-page");

		List<User> listUser = DatabaseHelper.selectAllUsers();
		req.setAttribute("listUsers", listUser);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsps/UsersList.jsp");
		dispatcher.forward(req, resp);

	}
}