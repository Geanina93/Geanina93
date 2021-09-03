package banking.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.ValidationException;
import banking.model.InfoUser;
import banking.model.TransferDetails;
import banking.services.AccountService;
import banking.web.forms.TransferForm;

public class TransferCont extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService;

	public TransferCont() {
		accountService = new AccountService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		goToTransferPage(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doTransfer(request, response);
		goToTransferPage(request, response);

	}

	private void goToTransferPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/transferCont.jsp").forward(
				request, response);
	}

	private void doTransfer(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute(
				"user");
		
		Integer userId = getInfoUser.getId();

		TransferForm detaliTransferForm = TransferForm.fromRequest(request);
		

		try {
			TransferDetails transferDetails = detaliTransferForm.toModel();
			try {
				accountService.doTransfer(transferDetails, userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				accountService.addTransferToTranzacti(transferDetails);
			} catch (Exception e) {
				e.printStackTrace();
			}

			request.setAttribute("message", "Transferul s-a facut cu succes.");
		} catch (ValidationException ve) {
			request.setAttribute("message", ve.getMessage());
		}

	}
}
