package io.tehtotalpwnage.pvptoggle.listeners;

import java.util.UUID;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;
import ninja.leaping.configurate.ConfigurationNode;

public class LoginListener {
	
	@Listener
	public void onClientConnection(ClientConnectionEvent.Join event) {
		Player player = event.getTargetEntity();
		UUID uuid = player.getUniqueId();
		ConfigurationNode node = PlayerList.getInstance().getNode();
		if (node.getNode("players", uuid, "pvp").isVirtual()) {
			node.getNode("players", uuid, "pvp").setValue(false);
			PlayerList.getInstance().save();
			player.sendMessage(TranslationHelper.text("text.player.disabled", player.getLocale()));
			player.sendMessage(TranslationHelper.text("text.player.toggle", player.getLocale()));
		} else {
			if (node.getNode("players", uuid, "pvp").getBoolean()) {
				player.sendMessage(TranslationHelper.text("text.player.enabled", player.getLocale()));
				player.sendMessage(TranslationHelper.text("text.player.toggle", player.getLocale()));
			} else {
				player.sendMessage(TranslationHelper.text("text.player.disabled", player.getLocale()));
				player.sendMessage(TranslationHelper.text("text.player.toggle", player.getLocale()));
			}
		}
	}
}
