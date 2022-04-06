package com.howard.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import com.howard.domain.User;

public class UserForm {

	public static final String PHONE_REG = "/^09\\d{8}$/";

	@NotBlank
	private String userName;
	@Length(min = 6, message = "密碼至少需要6位")
	private String password;
	@Pattern(regexp = PHONE_REG, message = "請輸入正確的手機號碼")
	private String phone;
	@NotBlank
	@Email(message = "請輸入正確信箱格式")
	private String email;
	@NotBlank
	private String confirmPassword;

	public UserForm() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public User convertToUser() {
		User user = new UserFormConvert().convert(this);
		return user;
	}

	public class UserFormConvert implements FormConvert<UserForm, User> {
		@Override
		public User convert(UserForm userForm) {
			User user = new User();
			BeanUtils.copyProperties(userForm, user);
			return user;
		}
	}

	public Boolean confirmPassword() {
		if (this.getPassword().equals(this.getConfirmPassword()))
			return true;
		return false;
	}
}
