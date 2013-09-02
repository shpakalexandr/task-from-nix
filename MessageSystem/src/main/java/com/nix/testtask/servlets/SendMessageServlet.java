package com.nix.testtask.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.breadcrumbs.Breadcrumbs;
import com.nix.testtask.breadcrumbs.Node;
import com.nix.testtask.breadcrumbs.NodeNames;

public class SendMessageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(null);
		b.setParent2(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setCurrent(new Node(NodeNames.SEND_MESS));
		
		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/SendMessage.jsp");
		dispatcher.forward(req, resp);
	}
}
