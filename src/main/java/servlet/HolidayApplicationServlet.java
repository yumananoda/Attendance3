package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.HolidayApplicationDao;

/**
 * Servlet implementation class HolidaypplicationServlet
 */
@WebServlet("/HolidayApplicationServlet")
public class HolidayApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called_HolidaypplicationServlet");
		request.setCharacterEncoding("UTF-8");
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String name = request.getParameter("name");
		String date = request.getParameter("date");
		String reason = request.getParameter("reason");
		String note = request.getParameter("note");

		LocalDate localDate = LocalDate.parse(date);
		Date sqlDate = Date.valueOf(localDate);
		System.out.println(sqlDate);
		System.out.println(reason);
		HolidayApplicationDao holidayDao = new HolidayApplicationDao();
		holidayDao.registerHoliday(employeeCD2, sqlDate, reason, note);
		request.getRequestDispatcher("/HolidayApplicationComp.jsp").forward(request, response);
		
	}
}
