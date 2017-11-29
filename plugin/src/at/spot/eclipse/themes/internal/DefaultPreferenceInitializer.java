package at.spot.eclipse.themes.internal;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import at.spot.eclipse.themes.core.Activator;

/**
 * The Class DefaultPreferenceInitializer.
 */
public class DefaultPreferenceInitializer extends AbstractPreferenceInitializer {
	@Override
	public void initializeDefaultPreferences() {
		final IPreferenceStore preferenceStore = Activator.getDefault()
				.getPreferenceStore();
		preferenceStore.setDefault(Preferences.PRESENTATION_FONT_SIZE, 20);
		preferenceStore.setDefault(Preferences.PM_THEME_ID, Preferences.ALTERNATE_THEME_ID);
	}

}
