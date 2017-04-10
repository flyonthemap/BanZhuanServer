package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ��֤����֤
 */
@WebServlet(name = "CheckAuthCode", urlPatterns = { "/checkAuthCode" })
public class ComfirmAuthCode extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�������

		String authCode = request.getParameter("authCode");
		String phone = request.getParameter("phone");
	
		response.setContentType("application/json; charset=utf-8");

		PrintWriter out = response.getWriter();
		String result = null;
		if (authCode.equals("1234")) {
			// ��֤����֤�ɹ�
			result = "{\"code\":0}";
			out.write(result);
		} else {
			// ��֤����֤ʧ�ܵı�־
			result = "{\"code\":1}";
			out.write(result);
		}
		out.flush();
		out.close();
	}

}
