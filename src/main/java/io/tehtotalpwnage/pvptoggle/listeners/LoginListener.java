package io.tehtotalpwnage.pvptoggle.listeners;

import java.util.UUID;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;

import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.utils.Messages;
import ninja.leaping.configurate.ConfigurationNode;

public class LoginListener {
	
	@Listener
	public void onClientConnectionJoin(ClientConnectionEvent.Join event) {
		Messages messages = Messages.getMessages();
		Player player = event.getTargetEntity();
		UUID uuid = player.getUniqueId();
		ConfigurationNode node = PlayerList.getPlayerList().getNode();
		if (node.getNode("players", uuid, "pvp") == null) {
			node.getNode("players", uuid, "pvp").setValue(false);
			PlayerList.getPlayerList().save();
			player.sendMessage(messages.disabled);
			player.sendMessage(messages.toggle);
		} else {
			if (node.getNode("players", uuid.toString(), "pvp").getBoolean() == false) {
				player.sendMessage(messages.disabled);
				player.sendMessage(messages.toggle);
			} else {
				player.sendMessage(messages.enabled);
				player.sendMessage(messages.toggle);
			}
		}
	}
}
