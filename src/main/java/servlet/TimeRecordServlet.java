package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import dao.TimeRecordDao;
import models.ShiftBean;
import models.TimeRecordsBean;

/**
 * Servlet implementation class TimeRecordServlet
 */
@WebServlet("/TimeRecordServlet")
public class TimeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeCD = (String) session.getAttribute("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		
		TimeRecordDao timeRecordDao = new TimeRecordDao();
		ArrayList<TimeRecordsBean> timeRecords = timeRecordDao.getStatus(employeeCD2);
		System.out.println(timeRecords);
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);
		System.out.println(shift);
		
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper);
		String json = mapper.writeValueAsString(timeRecords);
		String json2 = mapper.writeValueAsString(shift);
		System.out.println(json);
		System.out.println(json2);
		
		session.setAttribute("timeRecords", json);
		session.setAttribute("shift", json2);
		request.getRequestDispatcher("/TimeRecord.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			
	}

}
