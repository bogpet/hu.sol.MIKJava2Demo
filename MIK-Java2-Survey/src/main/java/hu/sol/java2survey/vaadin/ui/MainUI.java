package hu.sol.java2survey.vaadin.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.service.SurveyService;
import hu.sol.java2survey.vaadin.view.NameListView;
import hu.sol.java2survey.vaadin.view.NotExistingView;
import hu.sol.java2survey.vaadin.view.StatisticView;

@SpringUI
@Theme("reindeer")
public class MainUI extends UI {

	private static final long serialVersionUID = 315473946065975706L;

	private VerticalLayout pageLayout;

	private List<Student> studentList;

	private Navigator navigator;

	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private SurveyService surveyService;

	private BeanContainer<Long, Student> studentTableDataSource;

	@Override
	protected void init(VaadinRequest request) {
		try {
			this.loadFromDao();
		} catch (Exception e) {
			Notification.show("Hiba az adatbázisban", Notification.Type.ERROR_MESSAGE);
		} finally {
			this.setContent(this.pageLayout);
		}
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

	private void loadFromDao() throws Exception {
		this.studentList = this.surveyService.listAllStudent();
	}

	private Component createStatisticTab() {
		VerticalLayout statisticTabLayout = new VerticalLayout();
		statisticTabLayout.setSizeFull();
		statisticTabLayout.setCaption("Statisztika");
		statisticTabLayout.setMargin(true);
		return statisticTabLayout;
	}

	private Component createTableTab() {
		VerticalLayout tableTabLayout = new VerticalLayout();
		tableTabLayout.setSizeFull();
		tableTabLayout.setCaption("Névsor");
		tableTabLayout.setMargin(true);

		Table studentTable = new Table();
		studentTable.setSizeFull();

		this.studentTableDataSource = new BeanContainer<>(Student.class);
		this.studentTableDataSource.setBeanIdProperty("id");

		studentTable.setContainerDataSource(this.studentTableDataSource);
		studentTable.setVisibleColumns("name", "email");
		studentTable.setColumnHeaders("Név", "E-mail");

		this.refreshStudentTableDataSource();

		tableTabLayout.addComponent(studentTable);
		return tableTabLayout;
	}

	private void refreshStudentTableDataSource() {
		this.studentTableDataSource.removeAllItems();
		this.studentTableDataSource.addAll(this.studentList);
	}

}
