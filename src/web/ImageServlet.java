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
 * 这是一个图片处理类，根据客户端提供的路径返回服务器上的图片资源
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取文件的路径
		String path = request.getParameter("name");
		InputStream is = getServletContext().getResourceAsStream(
				"/WEB-INF/" + path);
		// 3 获得输出流
		OutputStream os = response.getOutputStream();
		// 4 两个流对接
		IOUtils.copy(is, os);
	}

}
