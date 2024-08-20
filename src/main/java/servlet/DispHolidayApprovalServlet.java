package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import dao.HolidayDao;
import models.ApplicationBean;

/**
 * Servlet implementation class DispHolidayApprovalServlet
 */
@WebServlet("/DispHolidayApprovalServlet")
public class DispHolidayApprovalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispHolidayApprovalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeCD = (String)session.getAttribute("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		EmployeeDao employeeDao = new EmployeeDao();
		int storeCD = employeeDao.findStoreCD(employeeCD2);
		System.out.println("storeCD:" + storeCD);
		session.setAttribute("storeCD", storeCD);
		
		HolidayDao holidayDao = new HolidayDao();
		ArrayList<ApplicationBean> applicationListOfUnapproved = holidayDao.getholidayApplicationListOfUnapproved(storeCD);
		System.out.println(applicationListOfUnapproved);
		
		ArrayList<ApplicationBean> applicationListOfApproved = holidayDao.getholidayApplicationListOfApproved(storeCD);
		System.out.println(applicationListOfApproved);
		
		ArrayList<ApplicationBean> applicationListOfRejected = holidayDao.getholidayApplicationListOfRejected(storeCD);
		System.out.println(applicationListOfRejected);
		
		request.setAttribute("applicationListOfUnapproved", applicationListOfUnapproved);
		request.setAttribute("applicationListOfApproved", applicationListOfApproved);
		request.setAttribute("applicationListOfRejected", applicationListOfRejected);
		
		request.getRequestDispatcher("/HolidayApproval.jsp").forward(request, response);
	}

}
