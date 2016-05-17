package io.tehtotalpwnage.pvptoggle.listeners;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;

import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;
import ninja.leaping.configurate.ConfigurationNode;

public class PvPListener {
	
	@Listener
	public void onDamageEntity(DamageEntityEvent event, @First EntityDamageSource source) {
		ConfigurationNode node = PlayerList.getPlayerList().getNode();
		Entity entityTarget = event.getTargetEntity();
		if (source.getSource().getType() == EntityTypes.PLAYER) {
			Player cause = (Player) source.getSource();
			if (!node.getNode("players", cause.getUniqueId(), "pvp").getBoolean()) {
				if (entityTarget.getType() == EntityTypes.PLAYER) {
					event.setCancelled(true);
					cause.sendMessage(TranslationHelper.t("messages.player.attacker"));
					cause.sendMessage(TranslationHelper.t("messages.player.toggle"));
					return;
				}
			} else if (entityTarget.getType() == EntityTypes.PLAYER) {
				Player target = (Player) entityTarget;
				if (!node.getNode("players", target.getUniqueId(), "pvp").getBoolean()) {
						event.setCancelled(true);
						cause.sendMessage(TranslationHelper.t("messages.player.target"));
						target.sendMessage(TranslationHelper.t("messages.player.attacked"));
						return;
				}
			}
		}
	}
}
