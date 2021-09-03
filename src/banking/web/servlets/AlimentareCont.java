package banking.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.ValidationException;
import banking.model.AccountInfo;
import banking.model.InfoUser;
import banking.services.AccountService;
import banking.web.forms.AccountForm;

public class AlimentareCont extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService;

	public AlimentareCont() {
		accountService = new AccountService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		goToAlimentareContPage(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		alimentareCont(request, response);
		goToAlimentareContPage(request, response);
	}

	private void alimentareCont(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("alimentare cont ");

		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute(
				"user");
		Integer userId = getInfoUser.getId();

		AccountForm form = AccountForm.fromRequest(request);

		try {
			AccountInfo account = form.toModel();
			accountService.updateAlimentareCont(account, userId);
				
			accountService.addALimentareToTranzactii(account.getId(), account.getSuma());
			
			request.setAttribute("message", "Alimentare cu succes!");
			
		} catch (ValidationException ve) {
			ve.printStackTrace();
			request.setAttribute("message", ve.getMessage());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			request.setAttribute("message", "Suma introdusa nu este un numar!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
		}
	}

	private void goToAlimentareContPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/alimentareCont.jsp")
				.forward(request, response);
	}

}