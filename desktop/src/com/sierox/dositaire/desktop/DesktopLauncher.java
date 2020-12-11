package com.sierox.dositaire.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sierox.dositaire.Dositaire;
import com.sierox.dositaire.util.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.width = Constants.WIDTH;
		config.height = Constants.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new Dositaire(), config);
	}
}
