package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.BreakDao;
import dao.EmployeeDao;
import dao.HolidayDao;
import dao.ShiftDao;
import dao.TimeRecordDao;
import models.BreakBean;
import models.ExceptionShiftBean;
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
		int agreements = employeeDao.getAgreements(employeeCD2);
		System.out.println("agreements:" + agreements);
		
		TimeRecordDao timeRecordDao = new TimeRecordDao();
		ArrayList<TimeRecordsBean> timeRecords = timeRecordDao.getStatus(employeeCD2);
		System.out.println("timeRecord:" + timeRecords);
		
		BreakDao breakDao = new BreakDao();
		ArrayList<BreakBean> breaks = breakDao.getStatus(employeeCD2);
		System.out.println("breaks:" + breaks);
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);
		System.out.println("shift:" + shift);
		
		ArrayList<ExceptionShiftBean> exceptionAddShift = shiftDao.findExceptionAddShiftByEmployeeCD(employeeCD2);
		System.out.println("exceptionAddShift:" + exceptionAddShift);
		
		ArrayList<ExceptionShiftBean> exceptionRemoveShift = shiftDao.findExceptionRemoveShiftByEmployeeCD(employeeCD2);
		System.out.println("exceptionRemoveShift:" + exceptionRemoveShift);
		
		ArrayList<ExceptionShiftBean> exceptionChangeShift = shiftDao.findExceptionChangeShiftByEmployeeCD(employeeCD2);
		System.out.println("exceptionChangeShift:" + exceptionChangeShift);
		
		HolidayDao holidayDao = new HolidayDao();
		ArrayList<HolidayBean> holiday = holidayDao.findHoliDayByEmployeeCD(employeeCD2);
		System.out.println("holiday:" + holiday);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper);
		String json = mapper.writeValueAsString(timeRecords);
		String json1 = mapper.writeValueAsString(breaks);
		String json2 = mapper.writeValueAsString(shift);
		String json3 = mapper.writeValueAsString(exceptionAddShift);
		String json4 = mapper.writeValueAsString(exceptionRemoveShift);
		String json5 = mapper.writeValueAsString(exceptionChangeShift);
		String json6 = mapper.writeValueAsString(holiday);
		System.out.println(json);
		System.out.println(json1);
		System.out.println(json2);
		System.out.println(json3);
		System.out.println(json4);
		System.out.println(json5);
		System.out.println(json6);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("name", name);
		request.setAttribute("agreements", agreements);
		request.setAttribute("timeRecords", json);
		request.setAttribute("breaks", json1);
		request.setAttribute("shift", json2);
		request.setAttribute("exceptionAddShift", json3);
		request.setAttribute("exceptionRemoveShift", json4);
		request.setAttribute("exceptionChangeShift", json5);
		request.setAttribute("holiday", json6);
		request.getRequestDispatcher("/TimeRecord.jsp").forward(request, response);
	}
}
