package at.spot.eclipse.themes.core.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.State;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.WorkbenchWindow;

@SuppressWarnings("restriction")
public class MenuBarManager {
	private static final String TOGGLE_STATE_ID = "org.eclipse.ui.commands.toggleState";
	private static final String COMMAND_ID = "presentation-mode-ui.toggle-menu";

	private final List<IContributionItem> hiddenMenus = new ArrayList<>();

	public MenuBarManager() {
	}

	public void updateMenuToCommandState() {
		final ICommandService commandService = (ICommandService) PlatformUI.getWorkbench()
				.getService(ICommandService.class);
		final State state = commandService.getCommand(COMMAND_ID).getState(TOGGLE_STATE_ID);

		if ((boolean) state.getValue()) {
			hideMenu();
		}
	}

	public void hideMenu() {
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		final MenuManager menuManager = ((WorkbenchWindow) window).getMenuManager();

		for (final IContributionItem mItem : menuManager.getItems()) {
			menuManager.remove(mItem);
			hiddenMenus.add(mItem);
		}
		menuManager.update(true);
	}

	public void showMenus() {
		final IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		final MenuManager menuManager = ((WorkbenchWindow) window).getMenuManager();

		for (Iterator<IContributionItem> iterator = hiddenMenus.iterator(); iterator.hasNext();) {
			menuManager.add(iterator.next());
			iterator.remove();
		}
		menuManager.update(true);
	}

}
