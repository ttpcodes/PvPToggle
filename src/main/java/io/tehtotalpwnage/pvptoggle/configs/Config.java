package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	private String file = "config.conf";
	private Path path = Paths.get(PvPToggle.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode rootNode;
	
	public void load() {
		PvPToggle.getInstance().getLogger().info("Loading config...");
		if(!Files.exists(path)) {
			PvPToggle.getInstance().getLogger().info("Config doesn't exist. Creating player list...");
			try {
				Files.createFile(path);
				PvPToggle.getInstance().getLogger().info("Created player list at " + path);
				rootNode.getNode("locale").setValue("en")
					.setComment("The two letter language code for a locale. Default is 'en'.");
				PvPToggle.getInstance().getLogger().info("Populated inital config at " + path);
			} catch (Exception e) {
				PvPToggle.getInstance().getLogger().error("Error occured on creating config: " + e.getMessage());
			}
		}
		try {
			rootNode = loader.load();
			loader.save(rootNode);
			PvPToggle.getInstance().getLogger().info("Config loaded from " + path);
		} catch (Exception e) {
			PvPToggle.getInstance().getLogger().error("Error occured on loading config: " + e.getMessage());
		}
	}
	public void save() {
		try {
			PvPToggle.getInstance().getLogger().info("Saving config...");
			loader.save(rootNode);
			PvPToggle.getInstance().getLogger().info("Config saved.");
		} catch (Exception e) {
			PvPToggle.getInstance().getLogger().info("Error occured on saving config: " + e.getMessage());
		}
	}
}