package banking.services;

import java.util.List;

import banking.dao.UserDao;
import banking.model.InfoUser;

public class UserInfoService {
	private UserDao userDao;

	public UserInfoService() {
		userDao = new UserDao();
	}

	public InfoUser getInfoUser(String username) {

		InfoUser userInfo = userDao.getInfoUser(username);
		return userInfo;
	}

	public List<InfoUser> getInfoUser() {

		List<InfoUser> listaClienti = userDao.getInfoUser();
		return listaClienti;
	}

}
