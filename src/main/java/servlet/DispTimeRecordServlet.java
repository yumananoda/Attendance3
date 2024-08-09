package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import dao.HolidayDao;
import dao.ShiftDao;
import dao.TimeRecordDao;
import models.HolidayBean;
import models.ShiftBean;
import models.TimeRecordsBean;

/**
 * Servlet implementation class DispTimeRecordServlet
 */
@WebServlet("/DispTimeRecordServlet")
public class DispTimeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispTimeRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called TimneRecordServlet");
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		EmployeeDao employeeDao = new EmployeeDao();
		String name = employeeDao.getEmployeeName(employeeCD2);
		
		TimeRecordDao timeRecordDao = new TimeRecordDao();
		ArrayList<TimeRecordsBean> timeRecords = timeRecordDao.getStatus(employeeCD2);
		System.out.println("timeRecord:" + timeRecords);
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);
		System.out.println("shift:" + shift);
		
		HolidayDao holidayDao = new HolidayDao();
		ArrayList<HolidayBean> holiday = holidayDao.findHoliDayByEmployeeCD(employeeCD2);
		System.out.println("holiday:" + holiday);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper);
		String json = mapper.writeValueAsString(timeRecords);
		String json2 = mapper.writeValueAsString(shift);
		String json3 = mapper.writeValueAsString(holiday);
		System.out.println(json);
		System.out.println(json2);
		System.out.println(json3);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("name", name);
		request.setAttribute("timeRecords", json);
		request.setAttribute("shift", json2);
		request.setAttribute("holiday", json3);
		request.getRequestDispatcher("/TimeRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
	}

}
