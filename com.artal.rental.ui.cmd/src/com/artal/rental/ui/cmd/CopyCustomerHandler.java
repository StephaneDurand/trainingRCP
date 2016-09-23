package com.artal.rental.ui.cmd;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.artal.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CopyCustomerHandler {
	

	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Customer c) {
		
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		
		String textData = null;
//		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
//		if (currentSelection instanceof IStructuredSelection) {
//			IStructuredSelection iSel = (IStructuredSelection) currentSelection;
//			for (Iterator<?> it = iSel.iterator(); it.hasNext();) {
				textData = c.getDisplayName();
//			}
//		}
//		
		String rtfData = "{\\rtf1\\b\\i " + textData + "}";
		
		Image imgData = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
		
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		ImageTransfer imgTransfer = ImageTransfer.getInstance();
		
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer, imgTransfer};
		Object[] data = new Object[]{textData, rtfData, imgData.getImageData()};
		clipboard.setContents(data, transfers);
		clipboard.dispose();

	}
	
	@CanExecute
	public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) Object o) {
		if (o instanceof Customer) {
			return true;
		}
		return false;
	}

}
