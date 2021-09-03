package banking.web.forms;

import javax.servlet.http.HttpServletRequest;
import banking.ValidationException;
import banking.model.TransferDetails;
import banking.utils.StringUtils;

public class TransferForm {
	private String idContSursaAsString;
	private String sumaDisponibilaAsString;
	private String idClientDestinatieAsString;
	private String nrContDestinatieAsString;
	private String sumaTransferAsString;

	public static TransferForm fromModel(TransferDetails transferDetails) {
		TransferForm form = new TransferForm();
		form.idContSursaAsString = "";
		if (transferDetails.getIdContSursa() != null) {
			form.idContSursaAsString = transferDetails.getIdContSursa()
					.toString();
		}

		form.sumaDisponibilaAsString = "";
		if (transferDetails.getSumaDisponibila() != null) {
			form.sumaDisponibilaAsString = transferDetails.getSumaDisponibila()
					.toString();
		}

		form.idClientDestinatieAsString = "";
		if (transferDetails.getIdClientDestinatie() != null) {
			form.idClientDestinatieAsString = transferDetails
					.getIdClientDestinatie().toString();

		}

		form.nrContDestinatieAsString = "";
		if (transferDetails.getNrContDestinatie() != null) {
			form.nrContDestinatieAsString = transferDetails
					.getNrContDestinatie().toString();
		}

		form.sumaTransferAsString = "";
		if (transferDetails.getSumaTransfer() != null) {
			form.sumaTransferAsString = transferDetails.getSumaTransfer()
					.toString();
		}

		return form;

	}

	public static TransferForm fromRequest(HttpServletRequest request) {

		TransferForm form = new TransferForm();

		form.idContSursaAsString = request.getParameter("idContSursa");
		form.idClientDestinatieAsString = request
				.getParameter("idDestinatieClient");
		form.nrContDestinatieAsString = request
				.getParameter("nrContDestinatie");
		form.sumaTransferAsString = request.getParameter("sumaTransfer");
		form.sumaDisponibilaAsString = request.getParameter("sumaCont");

		return form;
	}

	public TransferDetails toModel() throws ValidationException {

		TransferDetails transferDetails = new TransferDetails();
		Integer idContSursa = null;
		if (StringUtils.isNotEmpty(idContSursaAsString)) {
			idContSursa = Integer.valueOf(idContSursaAsString);
		} else {
			throw new ValidationException("Alegeti contul sursa.");
		}
		transferDetails.setIdContSursa(idContSursa);

		Integer idClientDestinatie = null;
		if (StringUtils.isNotEmpty(idClientDestinatieAsString)) {
			idClientDestinatie = Integer.valueOf(idClientDestinatieAsString);
		} else {
			throw new ValidationException("Alegeti clientul destinatie.");
		}
		transferDetails.setIdClientDestinatie(idClientDestinatie);

		Integer idContDestinatie = Integer.valueOf(nrContDestinatieAsString);
		transferDetails.setNrContDestinatie(idContDestinatie);

		Double sumaDisponibila = new Double(sumaDisponibilaAsString);
		transferDetails.setSumaDisponibila(sumaDisponibila);

		Double sumaTransfer = null;
		if (StringUtils.isNotEmpty(sumaTransferAsString)) {
			try {
				sumaTransfer = new Double(sumaTransferAsString);
			} catch (NumberFormatException nfe) {
				throw new ValidationException("Suma trebuie sa fie numerica.",
						nfe);
			}
		}

		Integer compare = sumaDisponibila.compareTo(sumaTransfer);
		if (compare == -1) {
			throw new ValidationException("Suma este prea mare.");
		}

		Integer compareWithZero = Double.compare(sumaTransfer, 0);
		if (compareWithZero < 0) {
			throw new ValidationException("Suma este negativa.");
		}
		transferDetails.setSumaTransfer(sumaTransfer);

		return transferDetails;

	}

	public String getIdContSursaAsString() {
		return idContSursaAsString;
	}

	public void setIdContSursaAsString(String idContSursaAsString) {
		this.idContSursaAsString = idContSursaAsString;
	}

	public String getSumaDisponibilaAsString() {
		return sumaDisponibilaAsString;
	}

	public void setSumaDisponibilaAsString(String sumaDisponibilaAsString) {
		this.sumaDisponibilaAsString = sumaDisponibilaAsString;
	}

	public String getIdClientDestinatieAsString() {
		return idClientDestinatieAsString;
	}

	public void setIdClientDestinatieAsString(String idClientDestinatieAsString) {
		this.idClientDestinatieAsString = idClientDestinatieAsString;
	}

	public String getNrContDestinatieAsString() {
		return nrContDestinatieAsString;
	}

	public void setNrContDestinatieAsString(String nrContDestinatieAsString) {
		this.nrContDestinatieAsString = nrContDestinatieAsString;
	}

	public String getSumaTransferAsString() {
		return sumaTransferAsString;
	}

	public void setSumaTransferAsString(String sumaTransferAsString) {
		this.sumaTransferAsString = sumaTransferAsString;
	}

}