package banking.web.forms;

import javax.servlet.http.HttpServletRequest;

import banking.ValidationException;
import banking.model.UserModel;

public class LoginForm {

	private String usernameAsString;
	private String passwordAsString;

	public static LoginForm fromRequest(HttpServletRequest request) {

		LoginForm form = new LoginForm();
		form.usernameAsString = request.getParameter("username");
		form.passwordAsString = request.getParameter("password");
		return form;
	}

	public UserModel toModel() throws ValidationException {

		UserModel model = new UserModel();
		String username = usernameAsString;
		model.setUsername(username);
		String password = passwordAsString;
		model.setPassword(password);
		return model;
	}

	public static LoginForm fromModel(UserModel model) {

		LoginForm form = new LoginForm();
		form.usernameAsString = model.getUsername();
		form.passwordAsString = model.getPassword();
		return form;
	}

	public String getUsernameAsString() {
		return usernameAsString;
	}

	public void setUsernameAsString(String usernameAsString) {
		this.usernameAsString = usernameAsString;
	}

	public String getPasswordAsString() {
		return passwordAsString;
	}

	public void setPasswordAsString(String passwordAsString) {
		this.passwordAsString = passwordAsString;
	}

}
