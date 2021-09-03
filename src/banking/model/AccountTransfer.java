package banking.model;

public class AccountTransfer {
	private Integer clientId;
	private Integer nrCont;
	private Double suma;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getNrCont() {
		return nrCont;
	}

	public void setNrCont(Integer nrCont) {
		this.nrCont = nrCont;
	}

	public Double getSuma() {
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

}
