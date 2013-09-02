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
import com.nix.testtask.db.DatabaseHelper;
import com.nix.testtask.db.User;

public class ModifyServlet extends HttpServlet{

	private static int id_Modify = 0;
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (id_Modify == 0) {
			int id = Integer.parseInt(req.getParameter("id"));
			setIdModify(id);
		}
		
		Breadcrumbs b = (Breadcrumbs) req.getSession().getAttribute("breadcrumbs");
		b.setParent1(new Node(NodeNames.MAIN, NodeNames.MAIN_URL));
		b.setParent2(new Node(NodeNames.USER_LIST, NodeNames.USER_LIST_URL));
		b.setCurrent(new Node(NodeNames.USER_MODIFY));
		
		User userInfo = DatabaseHelper.getUserByID(getIdModify());
		
		req.setAttribute("CREATE", false);
		req.setAttribute("userInfo", userInfo);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsps/CreateOrModifyUser.jsp");
		dispatcher.forward(req, resp);
	}
	public static int getIdModify() {
		return id_Modify;
	}
	public static void setIdModify(int id) {
		ModifyServlet.id_Modify = id;
	}

}
