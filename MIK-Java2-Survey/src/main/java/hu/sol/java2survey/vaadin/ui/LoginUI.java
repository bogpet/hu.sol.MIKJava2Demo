package hu.sol.java2survey.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;

import hu.sol.java2survey.vaadin.view.LoginView;

@Theme("reindeer")
@Widgetset("hu.sol.java2survey.vaadin.MyWidgetSet")
@SpringUI(path = "/login")
public class LoginUI extends UI {
	private static final long serialVersionUID = 4269249709825124375L;

	@Autowired
	private SpringNavigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		this.addStyleName("backColor");

		this.navigator.init(this, this);
		this.navigator.navigateTo(LoginView.VIEW_NAME);
	}
}
