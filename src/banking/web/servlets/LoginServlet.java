package banking.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.ValidationException;
import banking.model.InfoUser;
import banking.model.UserModel;
import banking.services.LoginService;
import banking.services.UserInfoService;
import banking.web.forms.LoginForm;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private LoginService loginService;
	private UserInfoService userInfoService;

	public LoginServlet() {
		loginService = new LoginService();
		userInfoService = new UserInfoService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		goToLoginPage(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		autentificare(request, response);
	}

	private void autentificare(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LoginForm form = LoginForm.fromRequest(request);
		request.setAttribute("form", form);

		try {
			UserModel model = form.toModel();
			loginService.LoginUser(model);
			request.setAttribute("message", "Autentificare reusita!");
			goToHomePage(request, response);

			InfoUser user = userInfoService.getInfoUser(form
					.getUsernameAsString());
			 //System.out.println("autentificare Nume:"+user.getNume());
			request.getSession().setAttribute("user", user);

		} catch (ValidationException ve) {
			request.setAttribute("message", ve.getMessage());
			goToLoginPage(request, response);
		}

	}

	private void goToLoginPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/login.jsp").forward(request,
				response);
	}

	private void goToHomePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() +"/home.do");
	}

}
