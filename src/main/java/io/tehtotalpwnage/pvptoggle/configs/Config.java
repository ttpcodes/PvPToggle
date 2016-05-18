package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class Config {
	
	private static Config instance = new Config();
	public static Config getInstance() {
		return instance;
	}
	
	public CommentedConfigurationNode getNode() {
		return rootNode;
	}
	
	private Logger logger = PvPToggle.getInstance().getLogger();
	
	private String file = "config.conf";
	private Path path = Paths.get(PvPToggle.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode rootNode;
	
	public void load() {
		logger.info("Loading config...");
		if(!Files.exists(path)) {
			logger.info("Config doesn't exist. Creating config...");
			try {
				Files.createFile(path);
				logger.info("Created config at " + path);
				rootNode = loader.load();
				rootNode.getNode("locale").setValue("en")
					.setComment("The two letter language code for a locale. Default is 'en'.");
				loader.save(rootNode);
				logger.info("Populated inital config at " + path);
			} catch (Exception e) {
				logger.error("Error occured on creating config: " + e.getMessage());
			}
		} else {
			try {
				rootNode = loader.load();
				loader.save(rootNode);
				logger.info("Config loaded from " + path);
			} catch (Exception e) {
				logger.error("Error occured on loading config: " + e.getMessage());
			}
		}
	}
	
	public void loadLocale() {
		if(Files.exists(path)) {
			
		} else {
			logger.error("Error loading locale from configuration file (First run perhaps?)");
		}
	}
	
	public void save() {
		try {
			logger.info("Saving config...");
			loader.save(rootNode);
			logger.info("Config saved.");
		} catch (Exception e) {
			logger.info("Error occured on saving config: " + e.getMessage());
		}
	}
}