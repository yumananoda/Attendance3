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
 * Servlet implementation class DispShiftRegisterServlet
 */
@WebServlet("/DispShiftRegisterServlet")
public class DispShiftRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DispShiftRegisterServlet() {
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
		System.out.println("employeeCD");
		System.out.println(employeeCD);
		
		ShiftDao shiftDao = new ShiftDao();
		ArrayList<ShiftBean> shift = shiftDao.findShiftByEmployeeCD(employeeCD2);

		//shiftをjson形式に変換
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(shift);
		System.out.println(shift);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("shift", json);
		request.getRequestDispatcher("/ShiftRegister.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
	}

}
