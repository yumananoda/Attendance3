package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import models.SelectEmployeeBean;

/**
 * Servlet implementation class SelectEmployeeServlet
 */
@WebServlet("/SelectEmployeeServlet")
public class SelectEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String managerCD = (String)session.getAttribute("employeeCD");
		int managerCD2 = Integer.parseInt(managerCD);

		EmployeeDao employeeDao = new EmployeeDao();
		int storeCD = employeeDao.findStoreCD(managerCD2);
		
		ArrayList<SelectEmployeeBean> selectEmployee = employeeDao.findEmployeeCDOfShiftRegister(storeCD);
		session.setAttribute("selectEmployee", selectEmployee);
		request.getRequestDispatcher("/SelectEmployee.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
		
		
		
	}
}
