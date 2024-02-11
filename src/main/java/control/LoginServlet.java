package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * ログイン処理を行うサーブレット
 * @author M.Takahashi
 */
@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");

		// ログイン処理
		String url;						// 転送先URL
		if (password.equals("pass")) {
			// ★ここでは、password が "pass" の場合、ログイン完了
			HttpSession session = request.getSession();
//			session.setMaxInactiveInterval(5);				// セッションタイムアウト(既定値=1800)設定
			session.setAttribute("userId", userId);
			url = "first.jsp";
		} else {
			request.setAttribute("errorMsg", "ユーザID または パスワードに 誤りがあります。");	
			url = "login.jsp";
		}

		// 転送
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
