package com.nix.testtask.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nix.testtask.breadcrumbs.Breadcrumbs;
import com.nix.testtask.breadcrumbs.Node;
import com.nix.testtask.breadcrumbs.NodeNames;
import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.Message;
import com.nix.testtask.db.User;

public class AdministrationSendedServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setParent2(new Node(NodeNames.USER_LIST, NodeNames.USER_LIST_URL));
		b.setCurrent(new Node(NodeNames.ADMIN_SENDED_MESS));

		if (req.getSession().getAttribute("UserName") == null) {

			int id = Integer.parseInt(req.getParameter("id"));

			User u = DatabaseHelper.getUserByID(id);
			List<Message> mesList = DatabaseHelper.selectUserMessage(
					"sended", u.getUsernickname());
			req.getSession().setAttribute("UserName", u.getUsernickname());
			req.setAttribute("username", u.getUsernickname());
			req.setAttribute("mesList", mesList);
		} else {
			String UserName = (String) req.getSession().getAttribute("UserName");
			List<Message> mesList = DatabaseHelper.selectUserMessage("sended", UserName);
			req.setAttribute("username", UserName);
			req.setAttribute("mesList", mesList);
		}

		RequestDispatcher dispatcher = req
				.getRequestDispatcher("/WEB-INF/jsps/AdministrationSended.jsp");
		dispatcher.forward(req, resp);
	}
}
