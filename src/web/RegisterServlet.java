package web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/user/reg")
public class RegisterServlet extends HttpServlet {
	private static final String USER_HAS_EXISTED = "{\"code\":1}";
	private static final String REGISTER_SUCCESS = "{\"code\":0}";
	private static final String PASSWORD = "password";
	private static final String PHONE_NUM = "phoneNum";
	private UserService us = new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		// 3 判断当前请求是否是多段式提交
		if (!upload.isMultipartContent(request)) {
			throw new RuntimeException("不是多段式提交!");
		}
		String filename = null;
		InputStream is = null;
		User u = new User();
		try {
			// 4 解析request对象
			List<FileItem> list = upload.parseRequest(request);
			if (list != null) {
				for (FileItem item : list) {
					// 判断当前上传段是普通字段,还是文件上传
					if (!item.isFormField()) {// 文件上传段
						String fName = item.getFieldName();
						filename = "E://" + item.getFieldName()
								+ File.separator + item.getName();
						is = item.getInputStream();// 获得文件上传段中,文件的流

					} else {// 普通字段段
						switch (item.getFieldName()) {
						case PASSWORD:
							u.setPassword(item.getString());
							break;
						case PHONE_NUM:
							u.setPhoneNum(item.getString());
							break;
						default:
							break;
						}
						String name = item.getFieldName();
						String value = item.getString();

						System.out.println(name + "==>" + value);

					}

				}
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int res = us.register(u);
		String result = null;
		switch (res) {
		case 0:
			result = REGISTER_SUCCESS;
			FileOutputStream fos = new FileOutputStream(filename);
			IOUtils.copy(is, fos);
			fos.close();
			break;
		case 1:
			result = USER_HAS_EXISTED;
			break;
		default:
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
