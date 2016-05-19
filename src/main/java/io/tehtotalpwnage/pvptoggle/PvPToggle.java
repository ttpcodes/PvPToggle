package io.tehtotalpwnage.pvptoggle;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import com.google.common.collect.Sets;
import com.google.inject.Inject;

import io.tehtotalpwnage.pvptoggle.configs.Config;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.listeners.LoginListener;
import io.tehtotalpwnage.pvptoggle.listeners.MovementListener;
import io.tehtotalpwnage.pvptoggle.listeners.PvPListener;
import io.tehtotalpwnage.pvptoggle.utils.CommandToggle;
import io.tehtotalpwnage.pvptoggle.utils.PluginInfo;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;

@Plugin(authors = PluginInfo.AUTHOR, description = PluginInfo.DESCRIPTION, id = PluginInfo.ID, 
	name = PluginInfo.NAME, url = PluginInfo.URL, version = PluginInfo.VERSION)

public class PvPToggle {
	
	@Inject
	@ConfigDir(sharedRoot = false)
	private Path path;
	public Path getConfigPath() {
		return path;
	}
	
	@Inject
	private Logger logger;
	public Logger getLogger() {
		return logger;
	}
	
	private static PvPToggle instance;
	public static PvPToggle getInstance() {
		return instance;
	}
	
	public static Locale locale;
	
	public static Set<UUID> togglePlayers = Sets.newHashSet();
	
	@Listener
	public void onPreInitialization(GamePreInitializationEvent event) {
		instance = this;
		locale = Config.getInstance().loadLocale();
		logger.info(TranslationHelper.s("string.console.preinit", locale));
		if (!Files.exists(path)) {
			logger.info(TranslationHelper.s("string.console.createConfigDir", locale));
			try {
				Files.createDirectories(path);
				logger.info(TranslationHelper.s("string.console.createdConfigDir", locale, getConfigPath()));
			} catch (Exception e) {
				logger.error(TranslationHelper.s("string.console.errorConfigDir", locale, e.getMessage()));
			}
		}
		Config.getInstance().load();
		PlayerList.getInstance().load();
		logger.info(TranslationHelper.s("string.console.preinitComplete", locale));
	}
	
	@Listener
	public void onInitialization(GameInitializationEvent event) {
		logger.info(TranslationHelper.s("string.console.init", locale, PluginInfo.VERSION));
		logger.info("Registering listeners...");
		try {
			Sponge.getEventManager().registerListeners(this, new LoginListener());
			Sponge.getEventManager().registerListeners(this, new MovementListener());
			Sponge.getEventManager().registerListeners(this, new PvPListener());
			logger.info("Successfully registered listeners.");
		} catch (Exception e) {
			logger.info("Error on registering listeners: " + e.getMessage());
		}
		logger.info("Registering PvP command...");
		try {
			CommandSpec toggle = CommandSpec.builder()
				.description(Text.of("Toggles PvP status for the player."))
				.permission("pvptoggle.toggle")
				.arguments(GenericArguments.optional(GenericArguments.player(Text.of("player"))))
				.executor(new CommandToggle())
				.build();
			Sponge.getCommandManager().register(this, toggle, "pvp");
			logger.info("Successfully registered command.");
		} catch (Exception e) {
			logger.info("Error on registering PvP command: " + e.getMessage());
		}
		logger.info(TranslationHelper.s("messages.console.initComplete", locale, PluginInfo.VERSION));
	}
}