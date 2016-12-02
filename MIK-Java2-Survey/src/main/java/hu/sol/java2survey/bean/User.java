package hu.sol.java2survey.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_user")
public class User implements Serializable, org.springframework.security.core.userdetails.UserDetails {
	private static final long serialVersionUID = 5417124918041223052L;

	@Id
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;

	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;

	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(String userName, String password, boolean enabled) {
		super();
		this.username = userName;
		this.password = password;
		this.enabled = enabled;
	}

	public User(String userName, String password, boolean enabled, Set<UserRole> userRole) {
		super();
		this.username = userName;
		this.password = password;
		this.enabled = enabled;
		this.userRoles = userRole;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + ", enabled=" + enabled + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userRoles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
