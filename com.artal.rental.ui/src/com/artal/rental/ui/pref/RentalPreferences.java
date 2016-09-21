package com.artal.rental.ui.pref;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.RentalUIActivator;

public class RentalPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	
	public static String DISPLAY_NUMBER = "DISPLAY_NUMBER";

	public RentalPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
		setDescription("Configure Agencies Display ");
	}

	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new BooleanFieldEditor(DISPLAY_NUMBER, "Display number of children beside each node", getFieldEditorParent()));

	}

}
