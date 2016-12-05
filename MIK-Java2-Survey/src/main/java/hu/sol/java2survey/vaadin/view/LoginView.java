package hu.sol.java2survey.vaadin.view;

import org.vaadin.risto.formsender.FormSender;
import org.vaadin.risto.formsender.widgetset.client.shared.Method;

import com.vaadin.data.validator.NullValidator;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = LoginView.VIEW_NAME)
public class LoginView extends LoginForm implements View {
	private static final long serialVersionUID = 2992502147074865800L;
	public static final String VIEW_NAME = "login";

	Action action_ok = new ShortcutAction("Default key", ShortcutAction.KeyCode.ENTER, null);

	@Override
	protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {
		this.setSizeFull();
		VerticalLayout pageLayout = new VerticalLayout();
		pageLayout.setWidth("100%");
		Component loginDetailsForm = createLoginDetailsForm(userNameField, passwordField, loginButton);
		Component maintenanceMsg = this.createAdditionalMsgs();
		pageLayout.addComponent(maintenanceMsg);
		pageLayout.addComponent(loginDetailsForm);
		pageLayout.setComponentAlignment(maintenanceMsg, Alignment.MIDDLE_CENTER);
		pageLayout.setComponentAlignment(loginDetailsForm, Alignment.MIDDLE_CENTER);
		this.addLoginListener(this.createLoginListener());
		return pageLayout;
	}

	private LoginListener createLoginListener() {
		return event -> {
			FormSender sender = new FormSender();
			sender.setFormAction("/j_spring_security_check");
			sender.setFormMethod(Method.POST);
			System.out.println(event.getLoginParameter("username"));
			System.out.println(event.getLoginParameter("password"));
			sender.addValue("username", event.getLoginParameter("username"));
			sender.addValue("password", event.getLoginParameter("password"));
			sender.setFormTarget("_top");
			sender.extend(LoginView.this.getUI());
			sender.submit();
		};

	}

	private static Component createLoginDetailsForm(TextField usernameField, PasswordField passwordField,
			Button loginButton) {
		FormLayout loginDetailsForm = new FormLayout();
		loginDetailsForm.setSpacing(true);
		loginDetailsForm.setMargin(true);
		loginDetailsForm.setSizeUndefined();

		usernameField.setCaption("Felhasználónév");
		usernameField.setIcon(FontAwesome.USER);
		usernameField.addValidator(new NullValidator("Hiányzó felhasználónév", false));

		passwordField.setCaption("Jelszó");
		passwordField.setIcon(FontAwesome.LOCK);
		passwordField.addValidator(new NullValidator("Hiányzó jelszó", false));

		loginButton.setCaption("Bejelentkezés");
		loginDetailsForm.addComponents(usernameField, passwordField, loginButton);
		return loginDetailsForm;
	}

	private Component createAdditionalMsgs() {
		VerticalLayout msgLayout = new VerticalLayout();

		Object springException = this.getUI().getSession().getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
		if (springException instanceof org.springframework.security.authentication.BadCredentialsException) {
			msgLayout.addComponent(new Label("Hibás felhaszálónév vagy jelszó!"));
		}
		msgLayout.setSizeUndefined();
		return msgLayout;
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
