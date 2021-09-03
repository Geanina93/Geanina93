package banking.model;

import java.util.Date;

public class TransactionDetails {
	private Date date;
	private Integer contSursa;
	private Double soldFinalContSursa;
	private Integer contDestinatie;
	private Double soldFinalContDestinatie;
	private Double sumaTransfer;

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getContSursa() {
		return contSursa;
	}

	public void setContSursa(Integer contSursa) {
		this.contSursa = contSursa;
	}

	public Double getSoldFinalContSursa() {
		return soldFinalContSursa;
	}

	public void setSoldFinalContSursa(Double soldFinalContSursa) {
		this.soldFinalContSursa = soldFinalContSursa;
	}

	public Integer getContDestinatie() {
		return contDestinatie;
	}

	public void setContDestinatie(Integer contDestinatie) {
		this.contDestinatie = contDestinatie;
	}

	public Double getSoldFinalContDestinatie() {
		return soldFinalContDestinatie;
	}

	public void setSoldFinalContDestinatie(Double soldFinalContDestinatie) {
		this.soldFinalContDestinatie = soldFinalContDestinatie;
	}

	public Double getSumaTransfer() {
		return sumaTransfer;
	}

	public void setSumaTransfer(Double sumaTransfer) {
		this.sumaTransfer = sumaTransfer;
	}

}
