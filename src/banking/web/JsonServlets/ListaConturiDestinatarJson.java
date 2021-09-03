package banking.web.JsonServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.model.AccountTransfer;
import banking.services.AccountService;

public class ListaConturiDestinatarJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService;

	public ListaConturiDestinatarJson() {
		accountService = new AccountService();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		conturiDestToJson(request, response);
	}

	private void conturiDestToJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer clientId = Integer.valueOf(request.getParameter("clientId"));
		List<String> listaConturi = accountService
				.getNumereConturiByIdClient(clientId);

		String jsonConturiDest = "[";
		for (int i = 0; i < listaConturi.size(); i++) {
			jsonConturiDest += "{\"NrCont\": " + listaConturi.get(i) + "} ";
			if (i < listaConturi.size() - 1) {
				jsonConturiDest += ", ";
			} else {
				jsonConturiDest += "]";
			}
		}

		response.setContentType("application/json");
		System.out.println("contJson" +jsonConturiDest );
		PrintWriter out = response.getWriter();
		out.print(jsonConturiDest);
		out.flush();
	}
}
