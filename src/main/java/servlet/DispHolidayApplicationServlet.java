package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;

/**
 * Servlet implementation class DispHolidaypplicationServlet
 */
@WebServlet("/DispHolidayApplicationServlet")
public class DispHolidayApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispHolidayApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String employeeCD = (String)session.getAttribute("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		
		EmployeeDao employeeDao = new EmployeeDao();
		String name = employeeDao.getEmployeeName(employeeCD2);
		System.out.println("name:" + name);
		
		request.setAttribute("name", name);
		
		request.getRequestDispatcher("/HolidayApplication.jsp").forward(request, response);
	}
}
