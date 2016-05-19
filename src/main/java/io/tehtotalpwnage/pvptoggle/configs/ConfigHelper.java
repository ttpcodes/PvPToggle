package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import org.slf4j.Logger;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class ConfigHelper {
	
	private static ConfigHelper instance = new ConfigHelper();
	public static ConfigHelper getInstance() {
		return instance;
	}
	
	private Logger logger = PvPToggle.getInstance().getLogger();
	private Locale locale = PvPToggle.locale;
	
	public CommentedConfigurationNode load(String file, Path path, ConfigurationLoader<CommentedConfigurationNode> loader) {
		if(!Files.exists(path)) {
			try {
				Files.createFile(path);
				logger.info(TranslationHelper.s("string.console.createdConfig", locale, file));
				CommentedConfigurationNode node = loader.load();
				loader.save(node);
				return node;
			} catch (Exception e) {
				logger.error(TranslationHelper.s("string.console.errorCreateConfig", locale, e.getMessage()));
				return null;
			}
		} else {
			try {
				CommentedConfigurationNode node = loader.load();
				loader.save(node);
				logger.info("Config loaded from " + path);
				return node;
			} catch (Exception e) {
				logger.error("Error occured on loading config: " + e.getMessage());
				return null;
			}
		}
	}

	public void save(ConfigurationLoader<CommentedConfigurationNode> loader, CommentedConfigurationNode node) {
		try {
			logger.info("Saving config...");
			loader.save(node);
			logger.info("Config saved.");
		} catch (Exception e) {
			logger.info("Error occured on saving config: " + e.getMessage());
		}
	}
}