package edu.awieclawski.quiz.models;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.awieclawski.quiz.models.enums.Privileges;
import edu.awieclawski.quiz.models.enums.Statuses;

@Entity
@Table(name = "users_tb")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String userName;
	private String login;
	@JsonIgnore
	private String password;
	@JsonIgnore
	@Enumerated(EnumType.STRING)
	private Privileges privilege;
	@Enumerated(EnumType.STRING)
	private Statuses status;

	public User(Long userId, String userName, String login, String password, Privileges privilege, Statuses status) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.login = login;
		this.password = password;
		this.privilege = privilege;
		this.status = status;
	}
	
	public User(String userName, String login, String password, Privileges privilege, Statuses status) {
		super();
		this.userName = userName;
		this.login = login;
		this.password = password;
		this.privilege = privilege;
		this.status = status;
	}

	public User() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}

	public Statuses getStatus() {
		return status;
	}

	public void setStatus(Statuses status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", login=" + login 
//				+ ", password=" + password
				+ ", password=" + "not allowed"
				+ ", privilege=" + privilege + ", status=" + status + "]";
	}
	
	

}
