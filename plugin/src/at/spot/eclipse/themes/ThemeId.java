package at.spot.eclipse.themes;

/**
 * The Enum ThemeId.
 *
 * @author gayanper
 */
public enum ThemeId {
	MACOS("at.spot.eclipse.themes.macos");

	private String themeId;

	private ThemeId(final String themeId) {
		this.themeId = themeId;
	}

	/**
	 * Gets the string fully qualified theme id.
	 *
	 * @return the id
	 */
	public String getId() {
		return themeId;
	}

	public static ThemeId forId(final String id) {
		switch (id) {
		case "at.spot.eclipse.themes.material-light":
			return MACOS;
		default:
			return null;
		}
	}
}
