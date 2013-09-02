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

public class CreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setAttribute("CREATE", true);
		if (req.getAttribute("ErrMsgs") == null) {
			req.setAttribute("ErrMsgs", null);			
		} else {
			req.setAttribute("ErrMsgs", req.getAttribute("ErrMsgs"));
		}
		
		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setParent2(new Node(NodeNames.USER_LIST, NodeNames.USER_LIST_URL));
		b.setCurrent(new Node(NodeNames.USER_CREATE));

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/CreateOrModifyUser.jsp");
		dispatcher.forward(req, resp);
	}
}
