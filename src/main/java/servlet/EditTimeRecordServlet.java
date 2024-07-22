package servlet;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TimeRecordDao;

/**
 * Servlet implementation class EditTimeRecordServlet
 */
@WebServlet("/EditTimeRecordServlet")
public class EditTimeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTimeRecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String recordCD = request.getParameter("recordCD");
		int recordCD2 = Integer.parseInt(recordCD);
		System.out.println("recordCD:" + recordCD);

		String selectDate = request.getParameter("selectDateValue");
		System.out.println("selectDate:" + selectDate);
		
		String afterClockInTime = request.getParameter("afterClockInTime");
		Time afterClockInTime2 = Time.valueOf(afterClockInTime + ":00");
		System.out.println("afterClockInTime:" + afterClockInTime2);
		
		String afterClockOutTime = request.getParameter("afterClockOutTime");
		Time afterClockOutTime2 = Time.valueOf(afterClockOutTime + ":00");
		System.out.println("afterClockInTime:" + afterClockOutTime2);
		
		String dateTimeString1 = selectDate + " " + afterClockInTime2;
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime beforelocalDateTime = LocalDateTime.parse(dateTimeString1, formatter1);
        
        String dateTimeString2 = selectDate + " " + afterClockOutTime2;
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime afterlocalDateTime = LocalDateTime.parse(dateTimeString2, formatter2);
        
        System.out.println("beforelocalDateTime:" + beforelocalDateTime);
        System.out.println("afterlocalDateTime:" + afterlocalDateTime);
		
        
        TimeRecordDao timeRecordDao = new TimeRecordDao();
        timeRecordDao.updateTimeRecord(recordCD2, beforelocalDateTime, afterlocalDateTime);

        request.getRequestDispatcher("/TimeRecord.jsp").forward(request, response);

	}
}
