package com.artal.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.artal.rental.ui"; //$NON-NLS-1$
	
	public static final String IMG_CUSTOMER = "icons/Customers.png";
	public static final String IMG_RENTAL = "icons/Rentals.png";
	public static final String IMG_OBJECT = "icons/RentalObjects.png";
	public static final String IMG_AGENCY = "icons/Agency.png";

	// The shared instance
	private static RentalUIActivator plugin;
	
	private Map<String, PaletteDesc> palettesMap = new HashMap<String, PaletteDesc>();
	
	public Map<String, PaletteDesc> getPalettesMap() {
		return palettesMap;
	}

	/**
	 * The constructor
	 */
	public RentalUIActivator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		getExtensionsQuickAccess();
		readPalettes();
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		
	Bundle b = FrameworkUtil.getBundle(this.getClass());
	
	reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
	reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
	reg.put(IMG_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_OBJECT)));
	reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
	
	}
	
	private void getExtensionsQuickAccess() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if (e.getName().equals("view")) {
				System.out.println("Plugin : " + e.getNamespaceIdentifier() + "\t Vue : " + e.getAttribute("name"));				
			}
		}
	}

	
	private void readPalettes() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IExtensionPoint extensionPoint = reg.getExtensionPoint("com.artal.rental.ui.Palette");
		for (IExtension extension : extensionPoint.getExtensions()) {
			for (IConfigurationElement e : extension.getConfigurationElements()) {
				try {
					PaletteDesc pDesc = new PaletteDesc();
					pDesc.setName(e.getAttribute("name"));
					pDesc.setId(e.getAttribute("id"));
					IColorProvider provider = (IColorProvider) e.createExecutableExtension("class");
					pDesc.setProvider(provider);
					palettesMap.put(pDesc.getId(), pDesc);
				}
				catch (CoreException e1){
					e1.printStackTrace();
				}
			}
		}
	}
}
