package banking.web.JsonServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import banking.model.AccountInfo;
import banking.model.InfoUser;
import banking.services.AccountService;


public class ListaConturiJson extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AccountService accountService;

	public ListaConturiJson() {
		accountService = new AccountService();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		accountJson(request, response);
	}

	private void accountJson(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		InfoUser getInfoUser = (InfoUser) request.getSession().getAttribute(
				"user");
		
		List<AccountInfo> accountsInfo = accountService
				.viewAccountInfo(getInfoUser.getId());
	 

		String conturiClientLogat = "[";
 		for (int i = 0; i < accountsInfo.size(); i++){
 			conturiClientLogat += "{\"ID\": "+ accountsInfo.get(i).getId()+" , ";
 			conturiClientLogat += "\"NrCont\": "+ accountsInfo.get(i).getNrCont()+" , ";
 			conturiClientLogat += "\"Suma\": "+ accountsInfo.get(i).getSuma()+" }";
 			if (i < accountsInfo.size() - 1){
 				conturiClientLogat += ", ";
 			} else {
 				conturiClientLogat += "]";
 			}
		}
//System.out.println("Conturi transfer: "+ conturiClientLogat);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(conturiClientLogat);
		out.flush();
	}
}
