package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import models.ShiftBean;

/**
 * Servlet implementation class ShiftRegisterServlet
 */
@WebServlet("/ShiftRegisterServlet")
public class ShiftRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShiftRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("called shiftRegisterSer");
		ArrayList<ShiftBean> ShiftList = new ArrayList<ShiftBean>();
		ShiftDao shiftDao = new ShiftDao();

		StringBuilder sb = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line = reader.readLine();
		System.out.println("line:" + line);
		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String requestBody = sb.toString();
		ObjectMapper objectMapper = new ObjectMapper();
		System.out.println("requestBody:" + requestBody);
		List<Map<String, Object>> dataList = objectMapper.readValue(requestBody, List.class);
		int employeeCD = 0;
		
		for(Map<String, Object> data: dataList) {
			employeeCD =  (int) data.get("employeeCD");
			System.out.println(employeeCD);
			
			int shift_duration = (int) data.get("shift_duration");
			System.out.println(shift_duration);
			
			int shift_day = (int) data.get("shift_day");
			System.out.println(shift_day);
			
			System.out.println("data:" + data);
			String start_time = (String) data.get("start_time");
			Time start_time2 = Time.valueOf(start_time + ":00");
			System.out.println(start_time2);
			
			String end_time = (String) data.get("end_time");
			Time end_time2 = Time.valueOf(end_time + ":00");
			System.out.println(end_time2);

			ShiftBean shift = new ShiftBean(employeeCD, shift_duration, shift_day, start_time2, end_time2);
			ShiftList.add(shift);
		}
		System.out.println(ShiftList);
		shiftDao.shiftDelete(employeeCD);
		System.out.println("削除成功");
		for(ShiftBean ShiftRequest : ShiftList) {
			shiftDao.shiftRegister(ShiftRequest);
		}
		System.out.println(ShiftList);
	}

}
