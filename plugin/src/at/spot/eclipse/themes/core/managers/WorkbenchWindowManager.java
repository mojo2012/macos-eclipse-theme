package at.spot.eclipse.themes.core.managers;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import at.spot.eclipse.themes.core.Activator;

public class WorkbenchWindowManager {

	private final Set<IWorkbenchWindow> registered = new HashSet<>();
	private final PerspectiveListener perspectiveListener = new PerspectiveListener();

	public WorkbenchWindowManager() {
		PlatformUI.getWorkbench().addWindowListener(new WindowListener());
	}

	private class PerspectiveListener implements IPerspectiveListener {

		@Override
		public void perspectiveActivated(final IWorkbenchPage page, final IPerspectiveDescriptor perspective) {
			Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
		}

		@Override
		public void perspectiveChanged(final IWorkbenchPage page, final IPerspectiveDescriptor perspective,
				final String changeId) {
			Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
		}

	}

	private class WindowListener implements IWindowListener {

		@Override
		public void windowActivated(final IWorkbenchWindow window) {
			if (!registered.contains(window)) {
				window.addPerspectiveListener(perspectiveListener);
				Activator.getDefault().getMenuBarManager().updateMenuToCommandState();
				registered.add(window);
			}
		}

		@Override
		public void windowDeactivated(final IWorkbenchWindow window) {
		}

		@Override
		public void windowClosed(final IWorkbenchWindow window) {
			registered.remove(window);
		}

		@Override
		public void windowOpened(final IWorkbenchWindow window) {

		}

	}

	public void dispose() {
		registered.clear();
	}
}
