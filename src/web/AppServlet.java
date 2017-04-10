package web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/task/list")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1 准备工作
		// 设置content-type
		// response.setContentType(getServletContext().getMimeType("xxx.zip"));
		// 添加响应头 content-disposition
		// response.setHeader("Content-Disposition",
		// "attachment;filename=apache-tomcat-6.0.35.zip");
		// 2 获得输入流
		String path;
		int index = (Integer.valueOf(request.getParameter("index")) / 10) % 3;
		if (index == 0) {
			path = "applist1";
		} else if (index == 1) {
			path = "applist2";
		} else {
			path = "applist3";
		}
 
		InputStream is = getServletContext().getResourceAsStream(
				"/WEB-INF/" + path);
		// 3 获得输出流
		OutputStream os = response.getOutputStream();
		// 4 两个流对接
		IOUtils.copy(is, os);

	}

}
