package at.spot.eclipse.themes.core;

import org.eclipse.ui.IStartup;

public class PluginStartup implements IStartup {

	@Override
	public void earlyStartup() {
		System.out.println("plugin startup");
	}

}
