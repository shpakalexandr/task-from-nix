package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.Message;

public class PersonalReceivedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to sendmessage-page");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Message> mesList = DatabaseHelper.selectUserMessage("received",auth.getName());
		
		System.out.println(mesList.size() + " " + auth.getName());

		req.setAttribute("messList", mesList);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/PersonalReceived.jsp");
		dispatcher.forward(req, resp);
	}
}
