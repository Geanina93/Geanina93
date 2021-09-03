package banking.services;

import java.sql.Date;
import java.util.List;

import banking.dao.TranzactiiDao;
import banking.model.TransactionDetails;

public class TranzactiiService {
private TranzactiiDao tranzactiiDao;
	
	public TranzactiiService(){
		tranzactiiDao = new TranzactiiDao();
	}
	
	public List<TransactionDetails> getTrasactionList(Integer idCont, Date startDate, Date endDate)
	{
		List<TransactionDetails> tranzactii = tranzactiiDao.getTrasactionList(idCont, startDate, endDate);
		return tranzactii;
	}
	

}
