package at.spot.eclipse.themes.pm;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import at.spot.eclipse.themes.pm.commands.MenuBarManager;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "at.spot.eclipse.themes.pm"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	private MenuBarManager menuBarManager = new MenuBarManager();

	private WorkbenchWindowManager windowManager = new WorkbenchWindowManager();

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		windowManager.dispose();
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	public MenuBarManager getMenuBarManager() {
		return menuBarManager;
	}
}