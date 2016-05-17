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
		ConfigurationNode node = PlayerList.getPlayerList().getNode();
		if (node.getNode("players", uuid, "pvp").isVirtual()) {
			node.getNode("players", uuid, "pvp").setValue(false);
			PlayerList.getPlayerList().save();
			player.sendMessage(TranslationHelper.t("messages.player.disabled"));
			player.sendMessage(TranslationHelper.t("messages.player.toggle"));
		} else {
			if (node.getNode("players", uuid.toString(), "pvp").getBoolean()) {
				player.sendMessage(TranslationHelper.t("messages.player.enabled"));
				player.sendMessage(TranslationHelper.t("messages.player.toggle"));
			} else {
				player.sendMessage(TranslationHelper.t("messages.player.disabled"));
				player.sendMessage(TranslationHelper.t("messages.player.toggle"));
			}
		}
	}
}
