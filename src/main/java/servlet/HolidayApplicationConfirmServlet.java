package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HolidayApplicationConfirmServlet
 */
@WebServlet("/HolidayApplicationConfirmServlet")
public class HolidayApplicationConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayApplicationConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called holiday");
		request.setCharacterEncoding("UTF-8");
    	String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String name =request.getParameter("name");
		String startDate =request.getParameter("startDate");
		System.out.println(startDate);
		LocalDate localStartDate = LocalDate.parse(startDate);
		Date sqlStartDate = Date.valueOf(localStartDate);
		System.out.println(sqlStartDate);
		String endDate =request.getParameter("endDate");
		LocalDate localEndDate = LocalDate.parse(endDate);
		Date sqlEndDate = Date.valueOf(localEndDate);
		System.out.println(sqlEndDate);
		String reason =request.getParameter("reason");
		String note =request.getParameter("note");
		
		System.out.println(employeeCD2);
		System.out.println(name);
		System.out.println(sqlStartDate);
		System.out.println(sqlEndDate);
		System.out.println(reason);
		System.out.println(note);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("name", name);
		request.setAttribute("startDate", sqlStartDate);
		request.setAttribute("endDate", sqlEndDate);
		request.setAttribute("reason", reason);
		request.setAttribute("note", note);
		request.getRequestDispatcher("/HolidayApplicationConfirm.jsp").forward(request, response);
	}

}
