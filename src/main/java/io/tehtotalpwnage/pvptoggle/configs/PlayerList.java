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
	
	private static PlayerList playerlist = new PlayerList();
	public static PlayerList getPlayerList() {
		return playerlist;
	}

	private ConfigurationNode rootNode;
	ConfigurationLoader<CommentedConfigurationNode> loader;
	
	public void loadPlayerList(PvPToggle plugin) {
		plugin.getLogger().info("Loading player list...");
		Path potentialFile = Paths.get(plugin.getConfigPath() + "/playerlist.conf");
		loader = HoconConfigurationLoader.builder()
				.setPath(potentialFile).build();
		if(!Files.exists(potentialFile)) {
			plugin.getLogger().info("Player list doesn't exist. Creating player list...");
			try {
				Files.createFile(potentialFile);
				plugin.getLogger().info("Created player list at " + potentialFile);
			} catch (Exception e) {
				plugin.getLogger().error("Error occured on creating player list: " + e.getMessage());
			}
		}
		try {
			rootNode = loader.load();
			loader.save(rootNode);
			plugin.getLogger().info("Player list loaded from " + potentialFile);
		} catch (Exception e) {
			plugin.getLogger().error("Error occured on loading player list: " + e.getMessage());
		}
	}
	public void save() {
		try {
//			plugin.getLogger().info("Saving player list...");
			loader.save(rootNode);
//			plugin.getLogger().info("Player list saved.");
		} catch (Exception e) {
//			plugin.getLogger().info("Error occured on saving player list: " + e.getMessage());
		}
	}
	public ConfigurationNode getNode() {
		return rootNode;
	}
}
