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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		System.out.println("called");
		ArrayList<EmployeeBean> EmployeeRegisterList = new ArrayList<>();
		EmployeeDao employeeDao = new EmployeeDao();
		HttpSession session = request.getSession();
		System.out.println(session);
		String managerCD = (String)session.getAttribute("employeeCD");
		int managerCD2 = Integer.parseInt(managerCD);
		System.out.println(managerCD);

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
		
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("requestBody:");
		System.out.println(requestBody);
		List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
		System.out.println("dataList:");
		System.out.println(dataList);
		for(Map<String, Object> data: dataList) {
			String name = (String) data.get("name");
			// System.out.println(name);
			String email = (String) data.get("email");
			String position = (String)data.get("position");
			int position2 = Integer.parseInt(position);
			System.out.println(position);
			String hire_date = (String) data.get("hire_date");
			LocalDate localDate = LocalDate.parse(hire_date);
			Date sqlDate = Date.valueOf(localDate);
			
			//GeneratorPassword generatorPassword = new GeneratorPassword();
			//String password = generatorPassword.generate();
			String password ="aaaaa";
			// System.out.println(password);
			Integer employeeCD=null; //employeeCDは自動生成のためnull
			int storeCD = employeeDao.findStoreCD(managerCD2);
			
			EmployeeBean EmployeeRegisterRequest = new EmployeeBean(employeeCD, storeCD, position2, name, password, email, sqlDate);
			EmployeeRegisterList.add(EmployeeRegisterRequest);
		}
		for(EmployeeBean EmployeeRegister : EmployeeRegisterList) {
			employeeDao.Register(EmployeeRegister);
		}
		
	}

}
