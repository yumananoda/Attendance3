package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called EditTimeRecordServlet");
		request.setCharacterEncoding("UTF-8");
		String recordCD = (String)request.getParameter("recordCD");
		int recordCD2= Integer.parseInt(recordCD);
		System.out.println("recordCD:" + recordCD2);
		String clockInTime = (String)request.getParameter("clockInTime");
		System.out.println("clockInTime:" + clockInTime);
		String clockOutTime = (String)request.getParameter("clockOutTime");
		System.out.println("clockOutTime:" + clockOutTime);
		
		request.setAttribute("recordCD", recordCD2);
		request.setAttribute("clockInTime", clockInTime);
		request.setAttribute("clockOutTime", clockOutTime);
		
		request.getRequestDispatcher("/EditTimeRecord.jsp").forward(request, response);
	}
}
