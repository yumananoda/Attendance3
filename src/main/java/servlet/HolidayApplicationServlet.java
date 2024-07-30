package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		try {
			String employeeCD = request.getParameter("employeeCD");
			int employeeCD2 = Integer.parseInt(employeeCD);
			
			String name = request.getParameter("name");
			String date = request.getParameter("date");
			
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");   
			java.util.Date parsedDate;
		
			parsedDate = dateFormat.parse(date);
			java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
			System.out.println(sqlDate);
			
			String reason = request.getParameter("reason");
			String note = request.getParameter("note");
			
			HolidayApplicationDao holidyApplicationServlet = new HolidayApplicationDao();
			holidyApplicationServlet.registerHoliday(employeeCD2, sqlDate, reason, note);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
