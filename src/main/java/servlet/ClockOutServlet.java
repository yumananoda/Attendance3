/** 退勤 */
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
 * Servlet implementation class ClockOutServlet
 */
@WebServlet("/ClockOutServlet")
public class ClockOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClockOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		LocalDateTime now = LocalDateTime.now();
		RegistrationClockDao out = new RegistrationClockDao();
		
		out.registrationClockOut(now, employeeCD2);
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("clockIn");
		session.removeAttribute("breakOut");
		request.getRequestDispatcher("/Clock.jsp").forward(request, response);
	}
}
