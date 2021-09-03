package banking.web.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.ValidationException;
import banking.model.TransactionDetails;
import banking.services.TranzactiiService;
import banking.web.forms.TranzactiiForm;

public class TranzactiiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TranzactiiService tranzactiiservice;

	public TranzactiiServlet() {
		tranzactiiservice = new TranzactiiService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		goToTranzactiiContPage(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		TranzactiiForm tranzactiiForm = TranzactiiForm.fromRequest(request);

		try {
			Integer idCont = tranzactiiForm.parseIdContSursa();
			Date startDate = tranzactiiForm.parseStartDate();
			Date endDate = tranzactiiForm.parseEndDate();

			request.getSession().setAttribute("idCont", idCont);
			request.getSession().setAttribute("startDate", startDate);
			request.getSession().setAttribute("endDate", endDate);

			List<TransactionDetails> tranzactii = tranzactiiservice.getTrasactionList(idCont, startDate, endDate);
			request.getSession().setAttribute("listaTranzactii", tranzactii);
		} catch (ValidationException ve) {
			request.setAttribute("message", ve.getMessage());
		}

		goToTranzactiiContPage(request, response);

	}

	public void goToTranzactiiContPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/tranzactii.jsp")
				.forward(request, response);
	}

}
