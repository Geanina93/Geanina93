package banking.model;

public class AccountInfo {
	private Integer id;
	private String nrCont;
	private String banca;
	private Integer userId;
	private Double suma;
	private Integer stare;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNrCont() {
		return nrCont;
	}

	public void setNrCont(String nrCont) {
		this.nrCont = nrCont;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getSuma() {
		return suma;
	}

	public void setSuma(Double suma) {
		this.suma = suma;
	}

	public Integer getStare() {
		return stare;
	}

	public void setStare(Integer stare) {
		this.stare = stare;
	}

}
