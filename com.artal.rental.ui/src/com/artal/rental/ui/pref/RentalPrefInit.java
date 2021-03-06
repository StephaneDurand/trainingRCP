package com.artal.rental.ui.pref;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.artal.rental.ui.RentalUIActivator;

public class RentalPrefInit extends AbstractPreferenceInitializer {

	public RentalPrefInit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		store.setDefault(RentalColorPreferences.P_AGENCY_COLOR, StringConverter.asString(new RGB(100, 200, 250)));
		store.setDefault(RentalColorPreferences.P_CUST_COLOR, StringConverter.asString(new RGB(100, 200, 250)));
		store.setDefault(RentalColorPreferences.P_OBJECT_COLOR, StringConverter.asString(new RGB(100, 200, 250)));
		store.setDefault(RentalColorPreferences.P_RENTAL_COLOR, StringConverter.asString(new RGB(100, 200, 250)));
		store.setDefault(PalettesPreferences.PREF_PALETTE, "com.artal.rental.ui.defaultPalette");
	}

}
