package io.tehtotalpwnage.pvptoggle.configs;

import java.nio.file.Path;
import java.nio.file.Paths;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;

public class PlayerList {
	
	private static PlayerList instance = new PlayerList();
	public static PlayerList getInstance() {
		return instance;
	}
	
	public CommentedConfigurationNode getNode() {
		return node;
	}
	
	private String file = "playerlist.conf";
	private Path path = Paths.get(PvPToggle.getInstance().getConfigPath() + "/" + file);
	private ConfigurationLoader<CommentedConfigurationNode> loader = HoconConfigurationLoader.builder()
		.setPath(path).build();
	private CommentedConfigurationNode node;
	
	public void load() {
		node = ConfigHelper.getInstance().load(file, path, loader);
	}
	
	public void save() {
		ConfigHelper.getInstance().save(loader, node);
	}
}
