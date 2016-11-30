package hu.sol.java2survey.vaadin.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.service.SurveyService;

@SpringUI(path = "/main")
@Theme("reindeer")
public class MainUI extends UI {

	private static final long serialVersionUID = 315473946065975706L;

	private VerticalLayout pageLayout;

	private List<Student> studentList;

	@Autowired
	private SurveyService studentService;

	private BeanContainer<Long, Student> studentTableDataSource;

	@Override
	protected void init(VaadinRequest request) {
		try {
			this.loadFromDao();
			this.pageLayout = this.setPageLayout();
			this.setContent(this.pageLayout);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadFromDao() throws Exception {
		this.studentList = this.studentService.listAllStudent();
	}

	private VerticalLayout setPageLayout() {
		VerticalLayout pageLayout = new VerticalLayout();
		pageLayout.setSizeFull();
		pageLayout.setMargin(true);

		TabSheet pageTabSheet = new TabSheet();
		pageTabSheet.setSizeFull();

		pageTabSheet.addTab(this.createTableTab());
		pageTabSheet.addTab(this.createStatisticTab());

		pageLayout.addComponent(pageTabSheet);
		return pageLayout;
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
