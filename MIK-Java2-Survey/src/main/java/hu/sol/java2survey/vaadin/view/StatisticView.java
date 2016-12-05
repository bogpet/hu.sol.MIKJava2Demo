package hu.sol.java2survey.vaadin.view;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.addon.JFreeChartWrapper;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import hu.sol.java2survey.bean.Subject;
import hu.sol.java2survey.service.SurveyService;

@SpringView(name = StatisticView.VIEW_NAME)
public class StatisticView extends Panel implements View {
	private static final long serialVersionUID = -1061194348267068085L;
	public static final String VIEW_NAME = "statistic";

	private VerticalLayout pageLayout;

	@Autowired
	private SurveyService surveyService;

	@Override
	public void enter(ViewChangeEvent event) {
		this.setSizeFull();
		try {
			this.setContent(this.createStatisticLayout());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Component createStatisticLayout() throws Exception {
		VerticalLayout statisticTabLayout = new VerticalLayout();
		statisticTabLayout.setSizeFull();
		statisticTabLayout.setCaption("Statisztika");
		statisticTabLayout.setMargin(true);

		JFreeChartWrapper jFreeChartWrapper = this.createJFreeChartWrapper();

		statisticTabLayout.addComponent(jFreeChartWrapper);
		return statisticTabLayout;
	}

	private JFreeChartWrapper createJFreeChartWrapper() throws Exception {
		JFreeChart createchart = this.createchart(this.createDataset());
		return new JFreeChartWrapper(createchart);
	}

	private PieDataset createDataset() throws Exception {
		List<Subject> subjects = this.surveyService.getSubjects();
		// row keys...
		String series1 = "First";
		String series2 = "Second";
		String series3 = "Third";

		// column keys...
		String category1 = "Category 1";
		String category2 = "Category 2";
		String category3 = "Category 3";
		String category4 = "Category 4";
		String category5 = "Category 5";

		// create the dataset...
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		DefaultPieDataset pieDataset = new DefaultPieDataset();
		for (Subject subject : subjects) {
			pieDataset.setValue(subject.getSubjectName(), 2.0);
		}

		return pieDataset;

	}

	private JFreeChart createchart(PieDataset dataset) {

		JFreeChart freeChart = ChartFactory.createPieChart3D("Témák megoszlása kedvelés alapján", dataset, true, true,
				false);

		PiePlot3D piePlot3D = (PiePlot3D) freeChart.getPlot();

		piePlot3D.setForegroundAlpha(0.60f);
		double percent = 50.5;

		return freeChart;

	}

}
