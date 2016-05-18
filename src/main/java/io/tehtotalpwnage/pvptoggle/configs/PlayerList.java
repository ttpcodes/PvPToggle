package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class PlayerList {
	
	private static PlayerList instance = new PlayerList();
	public static PlayerList getInstance() {
		return instance;
	}
	
	public ConfigurationNode getNode() {
		return rootNode;
	}
	
	private String file = "playerlist.conf";
	private Path path = Paths.get(PvPToggle.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private ConfigurationNode rootNode;
	
	public void load() {
		PvPToggle.getInstance().getLogger().info("Loading player list...");
		if(!Files.exists(path)) {
			PvPToggle.getInstance().getLogger().info("Player list doesn't exist. Creating player list...");
			try {
				Files.createFile(path);
				PvPToggle.getInstance().getLogger().info("Created player list at " + path);
			} catch (Exception e) {
				PvPToggle.getInstance().getLogger().error("Error occured on creating player list: " + e.getMessage());
			}
		}
		try {
			rootNode = loader.load();
			loader.save(rootNode);
			PvPToggle.getInstance().getLogger().info("Player list loaded from " + path);
		} catch (Exception e) {
			PvPToggle.getInstance().getLogger().error("Error occured on loading player list: " + e.getMessage());
		}
	}
	public void save() {
		try {
			PvPToggle.getInstance().getLogger().info("Saving player list...");
			loader.save(rootNode);
			PvPToggle.getInstance().getLogger().info("Player list saved.");
		} catch (Exception e) {
			PvPToggle.getInstance().getLogger().info("Error occured on saving player list: " + e.getMessage());
		}
	}
}
