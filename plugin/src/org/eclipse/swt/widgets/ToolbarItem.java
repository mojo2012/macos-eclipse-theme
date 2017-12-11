package org.eclipse.swt.widgets;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.internal.cocoa.NSBox;
import org.eclipse.swt.internal.cocoa.NSButton;
import org.eclipse.swt.internal.cocoa.NSButtonCell;
import org.eclipse.swt.internal.cocoa.NSMenuItem;
import org.eclipse.swt.internal.cocoa.NSString;
import org.eclipse.swt.internal.cocoa.NSToolbarItem;
import org.eclipse.swt.internal.cocoa.NSView;
import org.eclipse.swt.internal.cocoa.OS;
import org.eclipse.swt.internal.cocoa.SWTBox;
import org.eclipse.swt.internal.cocoa.SWTButton;
import org.eclipse.swt.internal.cocoa.SWTButtonCell;
import org.eclipse.swt.internal.cocoa.SWTMenuItem;
import org.eclipse.swt.internal.cocoa.SWTView;

@SuppressWarnings("restriction")
public class ToolbarItem extends ToolItem {

	protected ToolbarItemType type;

	public ToolbarItem(ToolBar parent, int style, ToolbarItemType type, int index) {
		super(parent, style, index);
		this.type = type;
	}

	public ToolbarItem(ToolBar parent, int style, ToolbarItemType type) {
		super(parent, style);
		this.type = type;
	}

	@Override
	void createWidget() {
		super.createWidget();
	}

	@Override
	void createHandle() {
		if (parent.nsToolbar != null) {
			id = NSString.stringWith(String.valueOf(ToolBar.NEXT_ID++));
			id.retain();
			nsItem = ((NSToolbarItem) new NSToolbarItem().alloc()).initWithItemIdentifier(id);
			nsItem.setAction(OS.sel_sendSelection);
			nsMenuRep = ((NSMenuItem) new SWTMenuItem().alloc()).initWithTitle(NSString.string(), OS.sel_sendSelection,
					NSString.string());
			nsItem.setMenuFormRepresentation(nsMenuRep);
		}

		// begin change
		if ((style & SWT.SEPARATOR) != 0) {
			if (parent.nsToolbar != null) {
				if (ToolbarItemType.Button.equals(type)) {
					Button button = new Button(parent, SWT.PUSH);

					if (this.image != null) {
						button.setImage(this.image);
					} else {
						button.setText(this.text);
					}

					NSButton nsButton = (NSButton) button.view;
					nsButton.setBordered(true);
					nsButton.setBezelStyle(11l);

					view = button.view;
				} else {
					view = (NSView) new SWTView().alloc();
				}

				view.init();
			} else {
				NSBox widget = (NSBox) new SWTBox().alloc();
				widget.init();
				widget.setBoxType(OS.NSBoxSeparator);
				widget.setBorderWidth(0);
				view = widget;
			}
		} else {
			NSView widget = (NSView) new SWTView().alloc();
			widget.init();
			button = (NSButton) new SWTButton().alloc();
			button.init();
			/*
			 * Feature in Cocoa. NSButtons without borders do not leave any margin between
			 * their edge and their image. The workaround is to provide a custom cell that
			 * displays the image in a better position.
			 */
			NSButtonCell cell = (NSButtonCell) new SWTButtonCell().alloc().init();
			button.setCell(cell);
			cell.release();
			cell.setHighlightsBy(OS.NSContentsCellMask);
			cell.setBackgroundStyle(OS.NSBackgroundStyleRaised);
			button.setBordered(false);
			button.setAction(OS.sel_sendSelection);
			button.setTarget(button);
			if (nsMenuRep != null)
				nsMenuRep.setTarget(button);
			Font font = parent.font != null ? parent.font : parent.defaultFont();
			button.setFont(font.handle);
			button.setImagePosition(OS.NSImageOverlaps);
			button.setTitle(NSString.string());
			button.setEnabled(parent.getEnabled());
			widget.addSubview(button);
			view = widget;
		}
	}

}
