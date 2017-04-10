package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import domain.User;
import service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private UserService us = new UserService();
	private static final String USER_HAS_UNREGISTER = "{\"code\":2}";
	private static final String LOGIN_SUCCESS = "{\"code\":0}";
	private static final String PASSWORD_ERROR = "{\"code\":1}";
	private static final String PASSWORD = "password";
	private static final String PHONE_NUM = "phoneNum";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1 封装参数到User对象
			User u = new User();
			try {
				BeanUtils.populate(u, request.getParameterMap());
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		String result = null;
		int res = us.login(u);
		switch (res) {
		case 0:
			result = LOGIN_SUCCESS;
			request.getSession().setAttribute("user", u);
			break;
		case 1:
			result = PASSWORD_ERROR;
			break;
		case 2:
			result = USER_HAS_UNREGISTER;
			break;
		}
		response.setContentType("text/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(result);
		out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
