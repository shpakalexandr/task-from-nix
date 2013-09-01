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
import com.nix.testtask.db.User;

public class LoadUsersListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("going to list-page");

		List<User> listUser = DatabaseHelper.selectAllUsers();
		req.setAttribute("listUsers", listUser);
		req.getSession().setAttribute("UserName", null);
		
		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent2(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setCurrent(new Node(NodeNames.USER_LIST));

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsps/UsersList.jsp");
		dispatcher.forward(req, resp);

	}
}