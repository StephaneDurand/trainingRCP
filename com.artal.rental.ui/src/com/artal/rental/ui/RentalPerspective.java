package com.artal.rental.ui;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class RentalPerspective implements IPerspectiveFactory {

	/**
	 * Creates the initial layout for a page.
	 */
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		String editorArea = layout.getEditorArea();
		addFastViews(layout);
		addViewShortcuts(layout);
		addPerspectiveShortcuts(layout);
		layout.addView("com.artal.rental.ui.views.RentalPropertyView", IPageLayout.TOP, 0.95f, IPageLayout.ID_EDITOR_AREA);
		layout.addView("com.artal.rental.ui.views.RentalAgencyView", IPageLayout.RIGHT, 0.5f, "com.artal.rental.ui.views.RentalPropertyView");
		layout.addView("com.artal.rental.ui.views.RentalCustomerView", IPageLayout.BOTTOM, 0.5f, "com.artal.rental.ui.views.RentalPropertyView");
	}

	/**
	 * Add fast views to the perspective.
	 */
	private void addFastViews(IPageLayout layout) {
	}

	/**
	 * Add view shortcuts to the perspective.
	 */
	private void addViewShortcuts(IPageLayout layout) {
	}

	/**
	 * Add perspective shortcuts to the perspective.
	 */
	private void addPerspectiveShortcuts(IPageLayout layout) {
	}

}
