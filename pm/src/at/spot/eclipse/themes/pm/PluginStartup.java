package at.spot.eclipse.themes.pm;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class PluginStartup implements IStartup {

	@Override
	public void earlyStartup() {
		System.out.println("plugin startup");

		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				for (IWorkbenchWindow w : workbench.getWorkbenchWindows()) {
					setupToolbar(w);
				}
			}
		});
	}

	protected void setupToolbar(IWorkbenchWindow window) {
		// experimentation
		final ToolBar tb = window.getShell().getToolBar();
		tb.setVisible(true);

		Image icon = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_BACK)
				.createImage();

		ToolItem toolItem = new ToolItem(tb, SWT.CHECK);
		toolItem.setText("test");
		toolItem.setWidth(100);
		toolItem.setImage(icon);

		// window.getShell().view.

		// experimentation end
	}
}
