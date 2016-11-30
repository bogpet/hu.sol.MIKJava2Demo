package hu.sol.java2survey.vaadin.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.service.SurveyService;

@SpringView(name = NameListView.VIEW_NAME)
public class NameListView extends Panel implements View {
	private static final long serialVersionUID = 1248237233538937001L;
	public static final String VIEW_NAME = "nameList";

	@Autowired
	private SurveyService surveyService;

	private BeanContainer<Long, Student> studentTableDataSource;

	@Override
	public void enter(ViewChangeEvent event) {
		this.setSizeFull();
		this.setContent(createTableLayout());
	}

	private Component createTableLayout() {
		VerticalLayout tableTabLayout = new VerticalLayout();
		tableTabLayout.setSizeFull();
		tableTabLayout.setCaption("Névsor");
		tableTabLayout.setMargin(true);

		Label titleLabel = new Label();
		titleLabel.setSizeUndefined();
		titleLabel.setCaptionAsHtml(true);
		titleLabel.setCaption("<h1>A feliratkozottak listája</h1>");

		Table studentTable = new Table();
		studentTable.setSizeFull();
		studentTable.setImmediate(true);

		this.studentTableDataSource = new BeanContainer<>(Student.class);
		this.studentTableDataSource.setBeanIdProperty("id");

		studentTable.setContainerDataSource(this.studentTableDataSource);
		studentTable.setVisibleColumns("name", "email");
		studentTable.setColumnHeaders("Név", "E-mail");

		tableTabLayout.addComponents(titleLabel, studentTable);
		tableTabLayout.setExpandRatio(studentTable, 1);
		return tableTabLayout;
	}

	@Scheduled(fixedDelay = 5000)
	private void refreshStudentTableDataSource() {
		try {
			if (this.studentTableDataSource != null) {
				this.studentTableDataSource.removeAllItems();
				this.studentTableDataSource.addAll(this.surveyService.listAllStudent());
			}
			if (this.getUI() != null) {
				this.getUI().push();
			}
		} catch (Exception e) {
			Notification.show("Hiba az adatbázis kapcsolattal", Notification.Type.WARNING_MESSAGE);
		}
	}

}
