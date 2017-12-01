package at.spot.eclipse.themes.ui;

import org.eclipse.swt.internal.cocoa.NSWindow;
import org.eclipse.swt.internal.cocoa.OS;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

@SuppressWarnings("restriction")
public class CocoaUtil {
	private static Long titleVisibilitySelector;

	public static void setWindowTitleVisible(Shell shell, boolean visible) {
		setWindowTitleVisible(shell.view.window(), false);
	}

	public static void setWindowTitleVisible(NSWindow window, boolean visible) {
		if (titleVisibilitySelector == null) {
			titleVisibilitySelector = OS.sel_registerName("setTitleVisibility:");
		}

		OS.objc_msgSend(window.id, titleVisibilitySelector, visible ? 0 : 1);
	}

	public static void allowsUserCustomizationSelector(ToolBar toolbar, boolean value) {
		toolbar.view.window().toolbar().setAllowsUserCustomization(value);
	}
}
