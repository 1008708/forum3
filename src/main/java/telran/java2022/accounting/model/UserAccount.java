package telran.java2022.accounting.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"login"})
@Document(collection = "users")
public class UserAccount {
	@Id
	String login;
	@Setter
	String password;
	@Setter
	String firstName;
	@Setter
	String lastName;
	Set<String> roles;
	@Setter
	LocalDate datePassword;

	public UserAccount() {
		roles = new HashSet<>();
		roles.add("USER");
		this.datePassword = LocalDate.now();
	}

	public UserAccount(String login, String password, String firstName, String lastName) {
		this();
		this.login = login;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.datePassword = LocalDate.now();
	}

	public boolean addRole(String role) {
		return roles.add(role);
	}

	public boolean removeRole(String role) {
		return roles.remove(role);
	}

}
