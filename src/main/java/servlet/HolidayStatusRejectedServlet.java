package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HolidayDao;
import models.ApplicationBean;

/**
 * Servlet implementation class HolidayStatusRejectedServlet
 */
@WebServlet("/HolidayStatusRejectedServlet")
public class HolidayStatusRejectedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HolidayStatusRejectedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("called statusRejected");
		String holidayCD = request.getParameter("holidayCD");
		int holidayCD2 = Integer.parseInt(holidayCD);
		System.out.println(holidayCD2);
		HttpSession session = request.getSession();
		int storeCD = (int) session.getAttribute("storeCD");
		System.out.println(storeCD);
		
		HolidayDao holidayDao = new HolidayDao();
		int status = 2;
		holidayDao.updateapprovalStatus(holidayCD2, status);
		
		ArrayList<ApplicationBean> applicationListOfUnapproved = holidayDao.getHoridayApplicationListOfUnapproved(storeCD);
		System.out.println(applicationListOfUnapproved);
		
		ArrayList<ApplicationBean> applicationListOfApproved = holidayDao.getHoridayApplicationListOfApproved(storeCD);
		System.out.println(applicationListOfApproved);
		
		ArrayList<ApplicationBean> applicationListOfRejected = holidayDao.getHoridayApplicationListOfRejected(storeCD);
		System.out.println(applicationListOfRejected);
		
		request.setAttribute("applicationListOfUnapproved", applicationListOfUnapproved);
		request.setAttribute("applicationListOfApproved", applicationListOfApproved);
		request.setAttribute("applicationListOfRejected", applicationListOfRejected);
		
		request.getRequestDispatcher("/HolidayApproval.jsp").forward(request, response);
	}

}
