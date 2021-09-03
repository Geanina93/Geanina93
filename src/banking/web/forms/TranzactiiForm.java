package banking.web.forms;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import banking.ValidationException;

public class TranzactiiForm {

	private String idContSursaAsString;
	private String startDateAsString;
	private String endDateAsString;
	
public static TranzactiiForm fromRequest(HttpServletRequest request){
		
	TranzactiiForm form = new TranzactiiForm();
		form.idContSursaAsString = request.getParameter("idCont");
		form.startDateAsString = request.getParameter("startDate");
		form.endDateAsString = request.getParameter("endDate");
		return form;
	}
	
	public Integer parseIdContSursa() throws ValidationException{
		Integer idContSursa = null;
		try{
			idContSursa = Integer.parseInt(idContSursaAsString);
		} catch(NumberFormatException nfe) {
			throw new ValidationException("Error parsing id cont!");
		}
		return idContSursa;
	}
	
	public Date parseStartDate() throws ValidationException{
		Date startDate;
		try{
			startDate = Date.valueOf(startDateAsString);
		} catch(NumberFormatException nfe) {
			throw new ValidationException("Error parsing start date!");
		}
		return startDate;
	}
	
	public Date parseEndDate() throws ValidationException{
		Date endDate;
		try{
			endDate = Date.valueOf(endDateAsString);
		} catch(NumberFormatException nfe) {
			throw new ValidationException("Error parsing end date!");
		}
		return endDate;
	}
	

	public String getIdContSursaAsString() {
		return idContSursaAsString;
	}

	public void setIdContSursaAsString(String idContSursaAsString) {
		this.idContSursaAsString = idContSursaAsString;
	}

	public String getStartDateAsString() {
		return startDateAsString;
	}

	public void setStartDateAsString(String startDateAsString) {
		this.startDateAsString = startDateAsString;
	}

	public String getEndDateAsString() {
		return endDateAsString;
	}

	public void setEndDateAsString(String endDateAsString) {
		this.endDateAsString = endDateAsString;
	}
}
