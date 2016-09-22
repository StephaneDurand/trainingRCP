package com.artal.rental.ui.pref;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.artal.rental.ui.PaletteDesc;
import com.artal.rental.ui.RentalUIActivator;

public class PalettesPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public static final String PREF_PALETTE = "PREF_PALETTE";

	public PalettesPreferences() {
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
		Map<String, PaletteDesc> palettes = RentalUIActivator.getDefault().getPalettesMap();
		
		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (PaletteDesc palette : palettes.values()) {
			comboValues[i][0] = palette.getName();
			comboValues[i][1] = palette.getId();	
			i++;
		}
		addField(new ComboFieldEditor(PREF_PALETTE, "Palette", comboValues, getFieldEditorParent()));

	}

}
