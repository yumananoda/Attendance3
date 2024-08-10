package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditEmployeeInfoConfirmServlet
 */
@WebServlet("/EditEmployeeInfoConfirmServlet")
public class EditEmployeeInfoConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeInfoConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("called editEmpInfoServ");
		request.setCharacterEncoding("UTF-8");
    	String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		String storeName =request.getParameter("storeName");
		String hireDate =request.getParameter("hireDate");
		String position =request.getParameter("position");
		
		
		System.out.println(employeeCD2);
		System.out.println(name);
		System.out.println(email);
		System.out.println(storeName);
		System.out.println(hireDate);
		System.out.println(position);
		
		request.setAttribute("employeeCD", employeeCD2);
		request.setAttribute("name", name);
		request.setAttribute("email", email);
		request.setAttribute("storeName", storeName);
		request.setAttribute("hireDate", hireDate);
		request.setAttribute("position", position);
		request.getRequestDispatcher("/EditEmployeeInfoConfirm.jsp").forward(request, response);
	}

}
