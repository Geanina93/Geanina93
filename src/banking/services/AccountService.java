package banking.services;

import java.util.List;

import banking.ValidationException;
import banking.dao.AccountDao;
import banking.model.AccountInfo;
import banking.model.AccountTransfer;
import banking.model.TransferDetails;

public class AccountService {
	private AccountDao accountDao;

	public AccountService() {
		accountDao = new AccountDao();
	}

	public List<String> getAccountsById(Integer userId) {

		List<String> account = accountDao.getAccountsById(userId);
		if (account == null) {
			throw new IllegalArgumentException("Utilizatorul cu  [" + userId
					+ "] nu are nici un cont.");
		}
		return account;
	}

	public void adaugaCont(String newAccountNr, Integer userId)
			throws ValidationException {
		// verific daca contul exista deja
		if (accountDao.checkNewAccountIfExist(newAccountNr)) {
			// arunci exceptie cod existent
			throw new ValidationException("Cont deja existent");
		}
		// salvezi cont
		accountDao.addNewAccount(newAccountNr, userId);

	}

	public List<AccountInfo> viewAccountInfo(Integer userId) {
		List<AccountInfo> accountsInfo = accountDao.viewAccountInfo(userId);
		return accountsInfo;
	}

	public void updateAlimentareCont(AccountInfo account, Integer userId)
			throws ValidationException {

		boolean verifyAccount = accountDao
				.updateAlimentareCont(account, userId);
		if (!verifyAccount) {
			throw new ValidationException("alimentare esuata");
		}
	}

	public List<String> getNumereConturiByIdClient(Integer clientId)
	{
		List<String> accounts = accountDao.getNumereConturiByIdClient(clientId);
		return accounts;
	}

	public void doTransfer(TransferDetails transferDetails, Integer userId)
			throws Exception {

		boolean transferCuSucces = accountDao.doTransfer(transferDetails,
				userId);
		if (!transferCuSucces) {
			throw new ValidationException("Transfer esuat!");
		}

	}

	public void addALimentareToTranzactii(Integer idCont, Double sumaAdaugata)
			throws Exception {
		accountDao.addALimentareToTranzactii(idCont, sumaAdaugata);

	}

	public void addTransferToTranzacti(TransferDetails transferDetails)
			throws Exception {
		accountDao.addTransferToTranzacti(transferDetails);
	}

}
