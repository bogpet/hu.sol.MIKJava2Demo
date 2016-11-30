package hu.sol.java2survey.vaadin.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = NameListView.VIEW_NAME)
public class NameListView extends Panel implements View {
	private static final long serialVersionUID = 1248237233538937001L;
	public static final String VIEW_NAME = "nameList";

	private VerticalLayout pageLayout;

	@Override
	public void enter(ViewChangeEvent event) {
		this.setSizeFull();
		Label errorLabel = new Label();
		errorLabel.setSizeUndefined();
		errorLabel.setCaptionAsHtml(true);
		errorLabel.setCaption("<h1>Névlista</h1>");
		this.pageLayout = new VerticalLayout();
		this.pageLayout.setSizeFull();
		this.pageLayout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		this.pageLayout.addComponent(errorLabel);
		this.setContent(this.pageLayout);
	}
}
