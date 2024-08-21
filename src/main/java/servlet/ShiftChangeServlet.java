package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import enums.ExceptionShiftCategoryEnum;

/**
 * Servlet implementation class ShiftChangeServlet
 */
@WebServlet("/ShiftChangeServlet")
public class ShiftChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiftChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("shiftChangeSer");
		
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
        
        String employeeCD = dataList.get(0);
        int employeeCD2 = Integer.parseInt(employeeCD);
        
        String changeDete = dataList.get(1);
		LocalDate localDate = LocalDate.parse(changeDete);
		Date sqlDate = Date.valueOf(localDate);
        
        String startTime = dataList.get(2);
		Time startTime2 = Time.valueOf(startTime + ":00");
		System.out.println("startTime:" + startTime2);
		
		String endTime = dataList.get(3);
		Time endTime2 = Time.valueOf(endTime + ":00");
		System.out.println("endTime:" + endTime2);

		String category = dataList.get(4);
		System.out.println(category);

		ExceptionShiftCategoryEnum category2 = ExceptionShiftCategoryEnum.getByLabel(category);
		int category3 = category2.getId();
		System.out.println(category2);
		System.out.println(category3);

		ShiftDao shiftDao = new ShiftDao();
		shiftDao.exceptionShiftRegister(employeeCD2, sqlDate, startTime2, endTime2, category3);
	}
}









