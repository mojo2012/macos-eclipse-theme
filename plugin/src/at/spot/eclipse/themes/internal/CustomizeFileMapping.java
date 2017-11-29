package at.spot.eclipse.themes.internal;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.URIUtil;

/**
 * The Class CustomizeMapping which keeps mapping for customization file against
 * each theme id.
 */
public final class CustomizeFileMapping {

	private static final String BASE_PATH = "platform:/config/at.spot.eclipse.themes/";

	private static final Map<ThemeId, String> MAPPINGS = new HashMap<>();

	static {
		MAPPINGS.put(ThemeId.MACOS, "macOS.css");
	}

	private CustomizeFileMapping() {
	}

	/**
	 * Customize file for given theme id.
	 *
	 * @param themeId
	 *            the theme id
	 * @return the file
	 */
	public static File customizeFile(final ThemeId themeId) throws IOException, URISyntaxException {
		final String fileName = MAPPINGS.get(themeId);
		final URL fileURL = FileLocator.toFileURL(URIUtil.toURL(new URI(BASE_PATH + fileName)));
		return new File(URIUtil.toURI(fileURL));
	}
}
