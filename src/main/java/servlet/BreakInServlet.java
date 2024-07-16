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
 * Servlet implementation class BreakInServlet
 */
@WebServlet("/BreakInServlet")
public class BreakInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BreakInServlet() {
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
		RegistrationClockDao breakIn = new RegistrationClockDao();
		breakIn.registrationBreakIn(now, employeeCD2);
		
		HttpSession session = request.getSession();
		session.setAttribute("breakIn",now);
		request.getRequestDispatcher("/Clock.jsp").forward(request, response);
	}

}
