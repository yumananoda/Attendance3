/** ログイン */
package servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
			int isAdmin = user.getIsAdmin();
			System.out.println(employeeCD);
			System.out.println(password);
			System.out.println(isAdmin);
			
			session.setAttribute("employeeCD", String.valueOf(user.getEmployeeCD()));
			session.setAttribute("name", String.valueOf(user.getName()));
			session.setAttribute("storeCD", String.valueOf(user.getStoreCD()));
			session.setAttribute("isAdmin", isAdmin);
			Timestamp clockIn = employeeDao.getClockIn(user.getEmployeeCD());
			if(clockIn != null) {
				LocalDateTime localDateTime = clockIn.toLocalDateTime();
				System.out.println(localDateTime);
				session.setAttribute("clockIn",localDateTime);
			}
			if(isAdmin == 1) {
				request.getRequestDispatcher("/ManagerHome.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/EmployeeHome.jsp").forward(request, response);
			}
			
		}else {
			System.out.print("ログイン失敗");
			request.setAttribute("message", "従業員コードかパスワードが違います。");
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
	}

}
