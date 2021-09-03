package banking.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import banking.model.InfoUser;
import banking.services.AccountService;

public class AdministreazaCont extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountService accountService;

	public AdministreazaCont() {
		accountService = new AccountService();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("AdministreazaCont doGet");
		getAccountsById(request, response);
		goToAccountPage(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		adaugaCont(request, response);
		getAccountsById(request, response);
		goToAccountPage(request, response);
	}

	private Integer getUserId(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute(
				"user");
		//System.out.println("InfoUser" + getInfoUser);
		Integer userId = getInfoUser.getId();
		return userId;
	}

	private void getAccountsById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer userId = getUserId(request, response);
		List<String> accountsList = accountService.getAccountsById(userId);
		request.getSession().setAttribute("accountsList", accountsList);

	}

	private void adaugaCont(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//iau userId de pe sesiune
		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute("user");
		//iau din body newAccountNr
		String newAccountNr = request.getParameter("newAccountNr");
		//apelez serviciu adaugare cont
		try {
			accountService.adaugaCont(newAccountNr, getInfoUser.getId());
//			System.out.println("Sa adaugat contul" +newAccountNr);
//			System.out.println(" user curent" + getInfoUser.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());			
		}
		
	}

	private void goToAccountPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/administrareCont.jsp")
				.forward(request, response);
	}
}
