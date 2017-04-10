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
 * 获取应用详情界面的信息
 */
@WebServlet("/app/detail")
public class AppDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String packName = request.getParameter("packName");
		// 获取文件的请求目录
		String path = "/WEB-INF/app/" + packName +"/" +packName;
		InputStream is = getServletContext().getResourceAsStream(path);
		// 3 获得输出流
		OutputStream os = response.getOutputStream();
		// 4 两个流对接
		IOUtils.copy(is, os);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
