package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ShiftDao;
import models.ShiftBean;

/**
 * Servlet implementation class DispShiftChangeServlet
 */
@WebServlet("/DispShiftChangeServlet")
public class DispShiftChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispShiftChangeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String employeeCD = (String)request.getParameter("employeeCD");
		int employeeCD2= Integer.parseInt(employeeCD);
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(shift);
		System.out.println(json);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("shift", json);
		request.getRequestDispatcher("/ShiftChange.jsp").forward(request, response);
	}
}