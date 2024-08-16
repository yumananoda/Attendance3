package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDao;

/**
 * Servlet implementation class EditPasswordServlet
 */
@WebServlet("/EditPasswordServlet")
public class EditPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		EmployeeDao employeeDao = new EmployeeDao();
		
		String employeeCD = request.getParameter("employeeCD");
		int employeeCD2 = Integer.parseInt(employeeCD);
		String correctPass = request.getParameter("correct");
		String currentPass = request.getParameter("current");
		String newPass = request.getParameter("new");
		
		System.out.println(correctPass);
		System.out.println(correctPass);
		System.out.println(!correctPass.equals(currentPass));
		if(!correctPass.equals(currentPass)) {
			request.setAttribute("employeeCD", employeeCD2);
			request.setAttribute("password", correctPass);
			request.setAttribute("message", "現在のパスワードが間違えていたため、変更できませんでした。");
			request.getRequestDispatcher("/EditPassword.jsp").forward(request, response);
		}else {
			employeeDao.updatePassword(newPass, employeeCD2);
			request.getRequestDispatcher("/EditPasswordComp.jsp").forward(request, response);
		}
	}
}
