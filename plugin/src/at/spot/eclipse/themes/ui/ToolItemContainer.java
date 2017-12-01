package at.spot.eclipse.themes.ui;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ToolItemContainer extends ToolItem {

	protected Control control;

	public ToolItemContainer(ToolBar parent, int style) {
		super(parent, style);
	}

	public void setControl(Control control) {
		this.control = control;
	}

	@Override
	public Control getControl() {
		return this.control;
	}

	@Override
	public Rectangle getBounds() {
		if (control != null) {
			return this.control.getBounds();
		}

		return super.getBounds();
	}

	@Override
	public boolean getEnabled() {
		if (control != null) {
			return this.control.getEnabled();
		}

		return super.getEnabled();
	}

	@Override
	public int getWidth() {
		if (control != null) {
			// x = width, y = height
			return this.control.getSize().x;
		}

		return super.getWidth();
	}

	@Override
	public boolean isEnabled() {
		return getEnabled();
	}

}
