package web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String range = req.getParameter("range");
		// 获取项目中的资源
		String path = getServletContext().getRealPath("/WEB-INF/" + name);
		File file = new File(path);
		long length = file.length();
		resp.setContentLength((int) length);
		resp.setContentType(getServletContext().getMimeType("xxx.apk"));
//		resp.setHeader("Content-Disposition", "attachment;filename="+name);
		OutputStream out = resp.getOutputStream();
		
		if(range == null ||"".equals(range.trim())){
			FileInputStream stream = new FileInputStream(file);
			int count = -1;
			byte[] buffer = new byte[1024];
			while ((count = stream.read(buffer)) != -1) {
			
				out.write(buffer, 0, count);
				out.flush();
			}
			stream.close();
			out.close();
		}else{
			RandomAccessFile raf = new RandomAccessFile(file, "r");
			raf.seek(Long.valueOf(range));
			int count = -1;
			byte[] buffer = new byte[1024];
			while ((count = raf.read(buffer)) != -1) {

				out.write(buffer, 0, count);
				out.flush();
			}
			raf.close();
			out.close();
		}
		
	}

}
