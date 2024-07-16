package servlet;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.EmployeeDao;
import models.EmployeeBean;
/** 
* Servlet implementation class DispEditEmployeeInfoServlet 
*/
@WebServlet("/DispEditEmployeeInfoServlet")
public class DispEditEmployeeInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/** 
	* @see HttpServlet#HttpServlet() 
	*/
	public DispEditEmployeeInfoServlet() {
		super();
		// TODO Auto-generated constructor stub 
	}
	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String employeeCD = (String) request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		EmployeeDao empDao = new EmployeeDao();
		EmployeeBean employeeInfo = empDao.getInfo(employeeCD2);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(employeeInfo);
		System.out.println(employeeInfo);
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("employeeInfo", json);
		request.getRequestDispatcher("/EditEmployeeInfo.jsp").forward(request, response);
	}
}