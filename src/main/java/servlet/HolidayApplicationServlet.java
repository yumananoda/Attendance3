package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;
import dao.HolidayDao;

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
		System.out.println("called_HolidayApplicationServlet");
		request.setCharacterEncoding("UTF-8");
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String name = request.getParameter("name");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String reason = request.getParameter("reason");
		String note = request.getParameter("note");

		LocalDate localStartDate = LocalDate.parse(startDate);
		Date sqStartlDate = Date.valueOf(localStartDate);
		System.out.println(sqStartlDate);
		LocalDate localEndDate = LocalDate.parse(endDate);
		Date sqEndDate = Date.valueOf(localEndDate);
		System.out.println(sqEndDate);
		System.out.println(reason);
		
		EmployeeDao employeeDao = new EmployeeDao();
		int storeCD = employeeDao.findStoreCD(employeeCD2);
		HolidayDao holidayDao = new HolidayDao();
		holidayDao.registerHoliday(employeeCD2, storeCD, sqStartlDate, sqEndDate, reason, note);
		request.getRequestDispatcher("/HolidayApplicationComp.jsp").forward(request, response);
		
	}
}
