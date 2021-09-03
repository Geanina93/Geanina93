package banking.services;

import banking.ValidationException;
import banking.dao.UserDao;
import banking.utils.StringUtils;
import banking.model.UserModel;

public class LoginService {

	private UserDao userDao;

	public LoginService() {
		userDao = new UserDao();
	}

	public void LoginUser(UserModel model) throws ValidationException {

		String username = model.getUsername();
		if (StringUtils.isEmpty(username)) {
			throw new ValidationException("Username missing !");
		}
		String password = model.getPassword();
		if (StringUtils.isEmpty(password)) {
			throw new ValidationException("Password missing !");
		}

		boolean verifyUser = userDao.checkUser(model.getUsername(),
				model.getPassword());
		if (!verifyUser) {
			throw new ValidationException("Login faild.");
		}

	}

}
