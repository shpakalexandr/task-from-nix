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

public class PersonalSendedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("going to sendmessage-page");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Message> mesList = DatabaseHelper.selectUserMessage("sended", auth.getName());
		
		req.setAttribute("username", auth.getName());
		req.setAttribute("mesList", mesList);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/PersonalSended.jsp");
		dispatcher.forward(req, resp);
	}
}
