package com.gabeWebTest.webTest;

import com.gabeWebTest.webTest.views.dashboard.DashboardView;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteNotFoundError;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point of our sprint boot application.
 */
@SpringBootApplication
@Theme("basetheme")
@Push
public class Application implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void configurePage(AppShellSettings settings) {
		// Add favicon
		settings.addFavIcon("icon", "icons/android-chrome-192x192.png", "192x192");
		// Add shortcut icon
		settings.addLink("shortcut icon", "icons/favicon.ico");
	}

}
