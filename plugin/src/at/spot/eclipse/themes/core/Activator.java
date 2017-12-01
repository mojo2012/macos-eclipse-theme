package at.spot.eclipse.themes.core;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.e4.ui.workbench.renderers.swt.TrimBarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import at.spot.eclipse.themes.ui.CocoaUtil;

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
					ToolBar tb = createNativeToolbar(window);
					tb.setLayout(new TrimBarLayout(true));

					createToolbarSeparator(tb);
					// createToolbarButton(tb, "Run", null);
					createToolbarButton(tb, "", getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Debug", getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Stop", getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Step into",
					// getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Step over",
					// getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Step out",
					// getImage(ISharedImages.IMG_TOOL_FORWARD));
					// createToolbarButton(tb, "Drop to frame",
					// getImage(ISharedImages.IMG_TOOL_FORWARD));

					List<String> runConfigs = new ArrayList<>();
					runConfigs.add("Run configurations ...");
					runConfigs.addAll(
							getAllLaunchConfigurations().stream().map(l -> l.getName()).collect(Collectors.toList()));
					createToolbarComboBox(tb, runConfigs);

				}
			}

		});
	}

	protected List<ILaunchConfiguration> getAllLaunchConfigurations() {
		try {
			return Arrays.asList(DebugPlugin.getDefault().getLaunchManager().getLaunchConfigurations());
		} catch (CoreException e) {
			e.printStackTrace();
		}

		return Collections.emptyList();
	}

	protected void createToolbarSeparator(ToolBar toolbar) {
		new ToolItem(toolbar, SWT.SEPARATOR);
	}

	protected Image getImage(String code) {
		return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(code).createImage();
	}

	protected void createToolbarComboBox(ToolBar toolbar, List<String> entries) {
		ToolItem toolItem = new ToolItem(toolbar, SWT.PUSH);
		Combo combobox = new Combo(toolbar, SWT.PUSH);
		combobox.setBounds(120, 4, 200, 33);
		combobox.setText("Run configurations ...");
		toolItem.setControl(combobox);

		if (entries != null) {
			combobox.setItems(entries.toArray(new String[0]));
			combobox.select(0);
		}
	}

	protected void createToolbarButton(ToolBar toolbar, String text, Image icon) {
		ToolItem toolItem = new ToolItem(toolbar, SWT.PUSH);

		Button button = new Button(toolbar, SWT.PUSH);
		toolItem.setControl(button);

		if (icon != null) {
			button.setImage(icon);
			toolItem.setText(text);
		} else {
			button.setText(text);
		}

		button.setBounds(75, 6, 50, 30);
	}

	protected ToolBar createNativeToolbar(IWorkbenchWindow window) {
		final ToolBar toolbar = window.getShell().getToolBar();
		toolbar.setVisible(true);
		CocoaUtil.setWindowTitleVisible(window.getShell(), false);
		CocoaUtil.allowsUserCustomizationSelector(toolbar, true);

		return toolbar;
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