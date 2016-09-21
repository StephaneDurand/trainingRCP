package com.artal.rental.ui.cmd;

import java.net.URL;
import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.URLTransfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.artal.rental.ui.RentalUIActivator;
import com.opcoach.training.rental.Customer;

public class CopyCustomerHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		
		String textData = null;
		ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
		if (currentSelection instanceof IStructuredSelection) {
			IStructuredSelection iSel = (IStructuredSelection) currentSelection;
			for (Iterator<?> it = iSel.iterator(); it.hasNext();) {
				textData = ((Customer) it.next()).getDisplayName();
			}
		}
		
		String rtfData = "{\\rtf1\\b\\i " + textData + "}";
		
		Image imgData = RentalUIActivator.getDefault().getImageRegistry().get(RentalUIActivator.IMG_CUSTOMER);
		
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		ImageTransfer imgTransfer = ImageTransfer.getInstance();
		
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer, imgTransfer};
		Object[] data = new Object[]{textData, rtfData, imgData.getImageData()};
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		return null;
	}

}
