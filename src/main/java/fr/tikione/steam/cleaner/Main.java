package fr.tikione.steam.cleaner;

import fr.tikione.ini.InfinitiveLoopException;
import fr.tikione.steam.cleaner.gui.dialog.JFrameMain;
import fr.tikione.steam.cleaner.util.Log;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

/**
 * Application launcher.
 */
public class Main {

	public static final String CONF_ENCODING = StandardCharsets.UTF_8.name();
	public static final String CONF_NEWLINE = "\r\n";
	public static boolean ARG_PORTABLE;

	/**
	 * The application launcher. Starts GUI.
	 *
	 * @param args command-line arguments.
	 */
	public static void main(String[] args) {

		// Detect portable mode.
		ARG_PORTABLE = Arrays.asList(args).contains("enablePortablemode");

		// Detect bundled JVM.
		File jre = new File("./jre/");
		boolean bundledJvm = jre.isDirectory() && jre.exists();

		Log.info("-------------------------------------------------");
		Log.info("Application started; version is " + Version.VERSION
				+ "; default encoding is " + CONF_ENCODING
				+ "; default locale is " + Locale.getDefault().toString()
				+ "; portableMode " + (ARG_PORTABLE ? "enabled" : "disabled")
				+ "; bundledJVM " + (bundledJvm ? "present" : "not found, will use system JVM"));
		try {
			javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			Log.error(e);
		}
		try {
			new JFrameMain().setVisible(true);
		} catch (IOException | InfinitiveLoopException ex) {
			Log.error(ex);
		}
	}

	/** Suppresses default constructor, ensuring non-instantiability. */
	private Main() {
	}
}
