package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RegistrationClockDao;

/**
 * Servlet implementation class BreakOutServlet
 */
@WebServlet("/BreakOutServlet")
public class BreakOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreakOutServlet() {
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
		LocalDateTime now = LocalDateTime.now();
		System.out.println(employeeCD);
		RegistrationClockDao breakOut = new RegistrationClockDao();
		breakOut.registrationBreakOut(now, employeeCD2);
		
		HttpSession session = request.getSession();
		session.setAttribute("breakOut",now);
		session.removeAttribute("breakIn");

		int isAdmin = (int) session.getAttribute("isAdmin"); 
		System.out.println(isAdmin);
		
		if(isAdmin == 1) {
			request.getRequestDispatcher("/ManagerHome.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("/EmployeeHome.jsp").forward(request, response);
		}
	}

}
