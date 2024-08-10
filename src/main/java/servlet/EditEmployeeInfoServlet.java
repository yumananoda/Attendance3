package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;

/**
 * Servlet implementation class EditEmployeeInfoServlet
 */
@WebServlet("/EditEmployeeInfoServlet")
public class EditEmployeeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String storeName = request.getParameter("storeName");
		int storeName2 = Integer.parseInt(storeName);
		String hireDate = request.getParameter("hireDate");
		String position = request.getParameter("position");
		int position2 = Integer.parseInt(position);
		System.out.println(employeeCD2);
		System.out.println(name);
		System.out.println(email);
		System.out.println(storeName2);
		System.out.println(hireDate);
		System.out.println(position2);
		
		EmployeeDao employeeDao = new EmployeeDao();
		employeeDao.updateEmployeeInfo(employeeCD2, name, storeName2, position2);
		request.getRequestDispatcher("/EditEmployeeInfoComp.jsp").forward(request, response);
	 }

}
