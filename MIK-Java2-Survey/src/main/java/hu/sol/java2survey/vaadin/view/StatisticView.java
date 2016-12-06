package hu.sol.java2survey.vaadin.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.JFreeChartWrapper;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.bean.Student;
import hu.sol.java2survey.bean.Subject;
import hu.sol.java2survey.listener.NewStudentListener;
import hu.sol.java2survey.service.SurveyService;

@SpringView(name = StatisticView.VIEW_NAME)
public class StatisticView extends Panel implements View, NewStudentListener {
	private static final long serialVersionUID = -1061194348267068085L;
	public static final String VIEW_NAME = "statistic";

	@Autowired
	private SurveyService surveyService;
	private DefaultPieDataset pieDataset;
	private List<Student> students;
	private List<Subject> subjects;

	@PostConstruct
	private void init() {
		this.surveyService.addNewStudentListener(this);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		this.setSizeFull();
		try {
			this.pieDataset = new DefaultPieDataset();
			this.students = this.surveyService.listAllStudent();
			this.subjects = this.surveyService.getSubjects();
			this.setContent(this.createStatisticLayout());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Component createStatisticLayout() {
		VerticalLayout statisticTabLayout = new VerticalLayout();
		statisticTabLayout.setSizeFull();
		statisticTabLayout.setCaption("Statisztika");
		statisticTabLayout.setMargin(true);

		JFreeChartWrapper jFreeChartWrapper = this.createJFreeChartWrapper();
		statisticTabLayout.addComponent(jFreeChartWrapper);
		statisticTabLayout.setComponentAlignment(jFreeChartWrapper, Alignment.MIDDLE_CENTER);

		this.recalculatePie();
		return statisticTabLayout;
	}

	private JFreeChartWrapper createJFreeChartWrapper() {
		JFreeChart createchart = createchart(this.pieDataset);
		return new JFreeChartWrapper(createchart);
	}

	private void recalculatePie() {
		this.pieDataset.clear();
		for (Subject subject : this.subjects) {
			// java 1.8 szükséges
			this.pieDataset.setValue(subject.getSubjectName(),
					this.students.stream().filter(o -> o.getSubjectId().equals(subject.getSubjectId())).count());
		}
	}

	private static JFreeChart createchart(PieDataset dataset) {
		JFreeChart freeChart = ChartFactory.createPieChart3D("Témák megoszlása kedvelés alapján", dataset, true, true,
				false);
		PiePlot3D piePlot3D = (PiePlot3D) freeChart.getPlot();
		piePlot3D.setForegroundAlpha(0.60f);
		return freeChart;

	}

	@Override
	public void handleNewStudent(Student student) {
		this.students.add(student);
		this.recalculatePie();
		if (this.getUI() != null && this.getUI().isAttached()) {
			this.getUI().push();
		}
	}

}
