/** ログイン */
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.EmployeeDao;
import models.EmployeeBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String employeeCD = request.getParameter("employeeCD");
		String password = request.getParameter("password");
		System.out.println(employeeCD);
		System.out.println(password);
		int employeeCD2 =Integer.parseInt(employeeCD);
		
		//アカウント検索
		EmployeeDao employeeDao = new EmployeeDao();
		EmployeeBean user = employeeDao.findUser(employeeCD2, password);
		System.out.println(user);
		
		//画面遷移
		if(user != null) {
			System.out.println("ログイン成功");
			session.setAttribute("employeeCD", String.valueOf(user.getEmployeeCD()));
			System.out.println(employeeCD);
//			System.out.println(password);
//			Timestamp clockIn = employeeDao.getClockIn(user.getEmployeeCD());
//			System.out.println(clockIn);
//			session.setAttribute("clockIn",clockIn);
//			Timestamp clockIn2 = session.getAttribute(clockIn);
//			System.out.println(clockIn2);
//			System.out.println(clockIn);
			
			request.getRequestDispatcher("/Clock.jsp").forward(request, response);
		}else {
			System.out.print("ログイン失敗");
			request.setAttribute("message", "従業員コードかパスワードが違います。");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
