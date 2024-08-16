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
 * Servlet implementation class DispEditPasswordServlet
 */
@WebServlet("/DispEditPasswordServlet")
public class DispEditPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispEditPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called DispEditPassServlet");
		HttpSession session = request.getSession();
		String employeeCD = (String) session.getAttribute("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		System.out.println(employeeCD);
		
		EmployeeDao employee = new EmployeeDao();
		String password = employee.getPassword(employeeCD2);
		System.out.println(password);
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("password", password);
		request.getRequestDispatcher("/EditPassword.jsp").forward(request, response);
	}
}
