package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.HolidayDao;
import dao.ShiftDao;
import models.ApplicationBean;
import models.ShiftBean;

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
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);
		System.out.println("shift:" + shift);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(shift);
		System.out.println(json);
		
		HolidayDao holidayDao = new HolidayDao();
//		有給残り日数を取得
		int currentYear = LocalDate.now().getYear();
		String currentYear2 = String.valueOf(currentYear);
		System.out.println(currentYear2);
		int restDays = holidayDao.getRestDays(employeeCD2, currentYear2);
		System.out.println("restDays:" + restDays);
		
		ArrayList<ApplicationBean> applicationList = holidayDao.getHolidayApplicationListByEmployeeCD(employeeCD2);
		System.out.println(applicationList);
		
		System.out.println("name:" + name);
		request.setAttribute("name", name);
		request.setAttribute("restDays", restDays);
		request.setAttribute("shift", json);
		request.setAttribute("applicationList", applicationList);
		
		request.getRequestDispatcher("/HolidayApplication.jsp").forward(request, response);
	}
}
