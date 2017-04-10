package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

@WebServlet("/user/authCode")
public class GetAuthCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private static final String CONFIRM_SUCCESS = "{\"code\":0}";
	private static final String PHONE_HAS_REGISTERED = "{\"code\":1}";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String phoneNum = request.getParameter("phoneNum");
		boolean exist = us.userIfRegistered(phoneNum);
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		if (exist == true) {
			out.write(PHONE_HAS_REGISTERED);
		} else{
			out.write(CONFIRM_SUCCESS);
		}
		out.flush();
		out.close();
	}

}
