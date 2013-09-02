package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.db.DatabaseHelper;

public class AdminDeleteReceivedMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		DatabaseHelper.removeFromReceived(id);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/AdministrationReceived");
		dispatcher.forward(req, resp);
	}
}