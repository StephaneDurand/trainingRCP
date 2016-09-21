package com.artal.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.RentalUIActivator;

public class RentalColorPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public static String P_CUST_COLOR = "P_CUST_COLOR";
	public static String P_RENTAL_COLOR = "P_RENTAL_COLOR";
	public static String P_OBJECT_COLOR = "P_OBJECT_COLOR";
	public static String P_AGENCY_COLOR = "P_AGENCY_COLOR";

	public RentalColorPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Configure display colors for the application");
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(P_AGENCY_COLOR, "Agency", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_CUST_COLOR, "Customer", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_OBJECT_COLOR, "Rental Objects", getFieldEditorParent()));
		addField(new ColorFieldEditor(P_RENTAL_COLOR, "Rental", getFieldEditorParent()));

	}

}
