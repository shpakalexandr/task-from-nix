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

import com.nix.testtask.breadcrumbs.Breadcrumbs;
import com.nix.testtask.breadcrumbs.Node;
import com.nix.testtask.breadcrumbs.NodeNames;
import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.Message;

public class PersonalReceivedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(null);
		b.setParent2(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setCurrent(new Node(NodeNames.PERSONAL_RECEIVED_MESS));
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		List<Message> mesList = DatabaseHelper.selectUserMessage("received", auth.getName());
		
		req.setAttribute("username", auth.getName());
		req.setAttribute("mesList", mesList);

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/PersonalReceived.jsp");
		dispatcher.forward(req, resp);
	}
}
