package at.spot.eclipse.themes.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import at.spot.eclipse.themes.core.managers.MenuBarManager;
import at.spot.eclipse.themes.core.managers.WorkbenchWindowManager;
import at.spot.eclipse.themes.internal.CustomizeFileMapping;
import at.spot.eclipse.themes.internal.ThemeId;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {
	private static Activator instance;

	// The plug-in ID
	public static final String PLUGIN_ID = "at.spot.eclipse.themes"; //$NON-NLS-1$

	private MenuBarManager menuBarManager = new MenuBarManager();

	private WorkbenchWindowManager windowManager = new WorkbenchWindowManager();

	public Activator() {
		instance = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);

		// Creates the custom css files.
		createIfNotExist(CustomizeFileMapping.customizeFile(ThemeId.MACOS));
		setupToolbar();
	}

	protected void setupToolbar() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().asyncExec(new Runnable() {
			public void run() {
				for (IWorkbenchWindow window : workbench.getWorkbenchWindows()) {
					final ToolBar tb = window.getShell().getToolBar();
					tb.setVisible(true);

					Image icon = PlatformUI.getWorkbench().getSharedImages()
							.getImageDescriptor(ISharedImages.IMG_TOOL_BACK).createImage();

					ToolItem toolItem = new ToolItem(tb, SWT.CHECK);
					toolItem.setText("test");
					toolItem.setWidth(100);
					toolItem.setImage(icon);
					// window.getShell().view.
				}
			}
		});
	}

	protected void createIfNotExist(final File file) throws IOException, MalformedURLException, URISyntaxException {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				if (!file.getParentFile().mkdirs()) {
					throw new IOException("Failed to create the missing dirs for " + file);
				}
			}

			file.createNewFile();
		}
	}

	public void stop(BundleContext context) throws Exception {
		windowManager.dispose();
		super.stop(context);
		instance = null;
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return instance;
	}

	public MenuBarManager getMenuBarManager() {
		return menuBarManager;
	}
}