package hu.sol.java2survey.vaadin.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.annotations.Widgetset;import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.vaadin.view.NameListView;
import hu.sol.java2survey.vaadin.view.NotExistingView;
import hu.sol.java2survey.vaadin.view.StatisticView;

@Push(PushMode.MANUAL)
@SpringUI
@Widgetset("hu.sol.java2survey.vaadin.MyWidgetSet")
@Theme("reindeer")
public class MainUI extends UI {

	private static final long serialVersionUID = 315473946065975706L;

	private VerticalLayout pageLayout;

	private Navigator navigator;

	@Autowired
	private SpringViewProvider viewProvider;

	@Override
	protected void init(VaadinRequest request) {
			this.setContent(this.pageLayout);
		this.pageLayout = new VerticalLayout();

		MenuBar menu = new MenuBar();
		menu.setWidth("100%");
		menu.addItem("Névsor", e -> this.getNavigator().navigateTo(NameListView.VIEW_NAME));
		menu.addItem("Statisztika", e -> this.navigator.navigateTo(StatisticView.VIEW_NAME));

		CssLayout contentLayout = new CssLayout();
		contentLayout.setSizeFull();
		this.pageLayout.addComponents(menu, contentLayout);
		this.pageLayout.setSizeFull();
		this.pageLayout.setExpandRatio(contentLayout, 1.0f);
		this.setContent(this.pageLayout);

		this.navigator = new Navigator(this, contentLayout);

		this.navigator.setErrorView(NotExistingView.class);
		this.navigator.addProvider(this.viewProvider);

		if ("".equals(this.navigator.getState())) {
			this.navigator.navigateTo(NameListView.VIEW_NAME);
		} else {
			this.navigator.navigateTo(this.navigator.getState());
	}

	}

	}
