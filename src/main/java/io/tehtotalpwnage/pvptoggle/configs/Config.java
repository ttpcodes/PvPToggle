package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.slf4j.Logger;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {
	
	private static Config instance = new Config();
	public static Config getInstance() {
		return instance;
	}
	
	public CommentedConfigurationNode getNode() {
		return node;
	}
	
	private Logger logger = PvPToggle.getInstance().getLogger();
	
	private String file = "config.conf";
	private Path path = Paths.get(PvPToggle.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode node;
	
	public void load() {
		if (!Files.exists(path)) {
			node = ConfigHelper.getInstance().load(file, path, loader);
			populate();
		} else {
			node = ConfigHelper.getInstance().load(file, path, loader);
		}
	}
	
	public Locale loadLocale() {
		try {
			Locale locale = new Locale(loader.load().getNode("locale").getValue().toString());
			logger.info("Loaded locale: '" + locale + "' for plugin PvPToggle");
			return locale;
		} catch (Exception e) {
			logger.error("Could not load locale (This is normal for first run.)");
			logger.info("Loaded default locale: '" + Locale.getDefault() + "' for plugin PvPToggle");
			return Locale.getDefault();
		}	
	}
	
	public void populate() {
		node.getNode("locale").setValue("en")
			.setComment("The two letter language code for a locale. Default is 'en'.");
		logger.info(TranslationHelper.s("string.console.populate", PvPToggle.locale, file));
	}
	
	public void save() {
		try {
			logger.info("Saving config...");
			loader.save(node);
			logger.info("Config saved.");
		} catch (Exception e) {
			logger.info("Error occured on saving config: " + e.getMessage());
		}
	}
}