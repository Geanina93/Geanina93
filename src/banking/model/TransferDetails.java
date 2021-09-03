package banking.model;

public class TransferDetails {
	private Integer idContSursa;
	private Double sumaDisponibila;
	private Integer idClientDestinatie;
	private Integer nrContDestinatie;
	private Double sumaTransfer;
	public Integer getIdContSursa() {
		return idContSursa;
	}
	public void setIdContSursa(Integer idContSursa) {
		this.idContSursa = idContSursa;
	}
	public Double getSumaDisponibila() {
		return sumaDisponibila;
	}
	public void setSumaDisponibila(Double sumaDisponibila) {
		this.sumaDisponibila = sumaDisponibila;
	}
	public Integer getIdClientDestinatie() {
		return idClientDestinatie;
	}
	public void setIdClientDestinatie(Integer idClientDestinatie) {
		this.idClientDestinatie = idClientDestinatie;
	}
	public Integer getNrContDestinatie() {
		return nrContDestinatie;
	}
	public void setNrContDestinatie(Integer nrContDestinatie) {
		this.nrContDestinatie = nrContDestinatie;
	}
	public Double getSumaTransfer() {
		return sumaTransfer;
	}
	public void setSumaTransfer(Double sumaTransfer) {
		this.sumaTransfer = sumaTransfer;
	}
}
