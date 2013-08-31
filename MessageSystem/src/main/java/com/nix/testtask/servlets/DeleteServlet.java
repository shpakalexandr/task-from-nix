package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;

public class DeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to delete-page");

		int id = Integer.parseInt(req.getParameter("id"));

		DatabaseHelper.deleteUser(id);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/UsersList");
		dispatcher.forward(req, resp);
	}
}
