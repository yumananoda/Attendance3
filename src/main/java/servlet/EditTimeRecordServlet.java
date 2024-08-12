package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("requestBody:" + requestBody);
        
        List<String> dataList = objectMapper.readValue(requestBody, List.class);
        
        String recordCD = dataList.get(0);
        int recordCD2 = Integer.parseInt(recordCD);
        String selectDate = dataList.get(1);
        String afterClockInTime = dataList.get(2);
        String afterClockOutTime = dataList.get(3);

        Time afterClockInTime2 = Time.valueOf(afterClockInTime + ":00");
        Time afterClockOutTime2 = Time.valueOf(afterClockOutTime + ":00");
		System.out.println("afterClockInTime:" + afterClockOutTime2);
		System.out.println("afterClockInTime:" + afterClockInTime2);
       
		String dateTimeString1 = selectDate + " " + afterClockInTime2;
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime beforelocalDateTime = LocalDateTime.parse(dateTimeString1, formatter1);
		  
		String dateTimeString2 = selectDate + " " + afterClockOutTime2;
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime afterlocalDateTime = LocalDateTime.parse(dateTimeString2, formatter2);

		
        if (afterlocalDateTime.isBefore(beforelocalDateTime)) {
            afterlocalDateTime = afterlocalDateTime.plusDays(1);
        }
        System.out.println("beforelocalDateTime:" + beforelocalDateTime);
        System.out.println("afterlocalDateTime:" + afterlocalDateTime);
        
        TimeRecordDao timeRecordDao = new TimeRecordDao();
        timeRecordDao.updateTimeRecord(recordCD2, beforelocalDateTime, afterlocalDateTime);

        request.getRequestDispatcher("/EditTimeRecordComp.jsp").forward(request, response);
	}
}
