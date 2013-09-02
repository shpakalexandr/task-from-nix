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

public class SuccessSendServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setParent2(new Node(NodeNames.SEND_MESS, NodeNames.SEND_MESS_URL));
		b.setCurrent(new Node(NodeNames.MESS_SENT_SUCCESS));

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/SuccessSend.jsp");
		dispatcher.forward(req, resp);
	}
}
