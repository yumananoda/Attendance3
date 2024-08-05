package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import models.EmployeeBean;
import models.ResponseMessage;

/**
 * Servlet implementation class EmployeeRegisterServlet
 */
@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("called register");
		ArrayList<EmployeeBean> EmployeeRegisterList = new ArrayList<>();
		EmployeeDao employeeDao = new EmployeeDao();
		HttpSession session = request.getSession();
		System.out.println(session);
		String managerCD = (String) session.getAttribute("employeeCD");
		int managerCD2 = Integer.parseInt(managerCD);
		System.out.println("managerCD" + managerCD);

		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader reader = request.getReader();
		line = reader.readLine();
		System.out.println(line);
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}
		System.out.println("line:");
		System.out.println(line);

		String requestBody = sb.toString();
		System.out.println(requestBody);

		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("requestBody:");
		System.out.println(requestBody);
		List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
		System.out.println("dataList:");
		System.out.println(dataList);
		for (Map<String, Object> data : dataList) {
			String name = (String) data.get("name");
			String email = (String) data.get("email");
			String position = (String) data.get("position");
			int position2 = Integer.parseInt(position);
			System.out.println(position);
			String hireDate = (String) data.get("hireDate");
			System.out.println(hireDate);
			LocalDate localDate = LocalDate.parse(hireDate);
			System.out.println(localDate);
			Date sqlDate = Date.valueOf(localDate);
			System.out.println(sqlDate);

			String password = (String) data.get("password");
			System.out.println(password);
			Integer employeeCD = null;
			int storeCD = employeeDao.findStoreCD(managerCD2);

			EmployeeBean EmployeeRegisterRequest = new EmployeeBean(employeeCD, storeCD, position2, name, password,
					email, sqlDate);
			EmployeeRegisterList.add(EmployeeRegisterRequest);
		}

		ArrayList<String> existsEmails = new ArrayList<>();
		for (EmployeeBean EmployeeRegister : EmployeeRegisterList) {
			String email = EmployeeRegister.getEmail();
			if (employeeDao.isEmailExists(email)) {
				existsEmails.add(email);
			}
		}

		if (existsEmails.size() == 0) {
			for (EmployeeBean EmployeeRegister : EmployeeRegisterList) {
				employeeDao.Register(EmployeeRegister);
				request.setAttribute("EmployeeRegisterList", EmployeeRegisterList);
				request.getRequestDispatcher("/EmployeeRegisterConfirm.jsp").forward(request, response);

			}
		} else {
			System.out.println(existsEmails);
			String message = "";
			message = String.join(",", existsEmails);
			message += "は登録済みのメールアドレスです。";
			System.out.println(message);
			response.setCharacterEncoding("UTF-8");
			ResponseMessage responseMessage = new ResponseMessage(message, true);
			String jsonResponse = objectMapper.writeValueAsString(responseMessage);
			response.getWriter().write(jsonResponse);
			return;
		}
	}
}
