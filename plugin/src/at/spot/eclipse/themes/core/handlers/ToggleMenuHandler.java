package at.spot.eclipse.themes.core.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.handlers.HandlerUtil;

import at.spot.eclipse.themes.core.Activator;

public class ToggleMenuHandler extends AbstractHandler {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final boolean state = HandlerUtil.toggleCommandState(event.getCommand());
		if (state == false) {
			Activator.getDefault().getMenuBarManager().hideMenu();
		} else {
			Activator.getDefault().getMenuBarManager().showMenus();
		}
		return null;
	}
}
