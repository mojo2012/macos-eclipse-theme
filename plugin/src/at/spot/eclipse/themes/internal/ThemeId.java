package at.spot.eclipse.themes.internal;

/**
 * The Enum ThemeId.
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
}
