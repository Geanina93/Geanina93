package banking.web.forms;

import javax.servlet.http.HttpServletRequest;

import banking.ValidationException;
import banking.model.AccountInfo;
import banking.model.InfoUser;

public class AccountForm {
	private String idAsString;
	private String nrContAsString;
	private String bancaAsString;
	private String userIdAsString;
	private String sumaAsString;
	private String stareAsString;

	public static AccountForm fromModel(AccountInfo account) {

		AccountForm form = new AccountForm();
		form.nrContAsString = "";
		if (account.getNrCont() != null) {
			form.nrContAsString = account.getNrCont().toString();
		}
		form.idAsString = "";
		if (account.getId() != null) {
			form.idAsString = account.getId().toString();
		}
		form.userIdAsString = "";
		if (account.getUserId() != null) {
			form.userIdAsString = account.getUserId().toString();
		}
		form.sumaAsString = "";
		if (account.getSuma() != null) {
			form.sumaAsString = account.getSuma().toString();
		}

		return form;
	}

	public static AccountForm fromRequest(HttpServletRequest request) {

		AccountForm form = new AccountForm();
		//get new account sum
		//System.out.println(request.getParameter("sumaCont"));
		form.sumaAsString = request.getParameter("sumaAdaugata"); //+ request.getParameter("sumaCont");
		form.userIdAsString = "" + ((InfoUser)request.getSession().getAttribute("user")).getId();
		form.idAsString = request.getParameter("idCont");

		return form;
	}

	public AccountInfo toModel() throws ValidationException {

		AccountInfo account = new AccountInfo();
		Integer UserID = Integer.parseInt(userIdAsString);
		account.setUserId(UserID);
		Integer ID = Integer.parseInt(idAsString);
		account.setId(ID);
		Double Suma = Double.parseDouble(sumaAsString);
		account.setSuma(Suma);

		return account;
	}

	public String getIdAsString() {
		return idAsString;
	}

	public void setIdAsString(String idAsString) {
		this.idAsString = idAsString;
	}

	public String getNrContAsString() {
		return nrContAsString;
	}

	public void setNrContAsString(String nrContAsString) {
		this.nrContAsString = nrContAsString;
	}

	public String getBancaAsString() {
		return bancaAsString;
	}

	public void setBancaAsString(String bancaAsString) {
		this.bancaAsString = bancaAsString;
	}

	public String getUserIdAsString() {
		return userIdAsString;
	}

	public void setUserIdAsString(String userIdAsString) {
		this.userIdAsString = userIdAsString;
	}

	public String getSumaAsString() {
		return sumaAsString;
	}

	public void setSumaAsString(String sumaAsString) {
		this.sumaAsString = sumaAsString;
	}

	public String getStareAsString() {
		return stareAsString;
	}

	public void setStareAsString(String stareAsString) {
		this.stareAsString = stareAsString;
	}

}
