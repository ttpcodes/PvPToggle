package io.tehtotalpwnage.pvptoggle.listeners;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.cause.entity.damage.source.IndirectEntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.filter.cause.First;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;
import ninja.leaping.configurate.ConfigurationNode;

public class PvPListener {
	
	@Listener
	public void onDamageEntity(DamageEntityEvent event, @First IndirectEntityDamageSource source) {
		ConfigurationNode node = PlayerList.getInstance().getNode();
		Entity entityTarget = event.getTargetEntity();
		if (source.getSource().getType() == EntityTypes.PLAYER || (source.getSource().getType() == EntityTypes.ARROW
			&& source.getIndirectSource().getType() == EntityTypes.PLAYER)) {
				Player cause = null;
				if (source.getSource().getType() == EntityTypes.PLAYER) {
					cause = (Player) source.getSource();
				} else {
					cause = (Player) source.getIndirectSource();
				}
				if (!node.getNode("players", cause.getUniqueId(), "pvp").getBoolean()) {
					if (entityTarget.getType() == EntityTypes.PLAYER) {
						event.setCancelled(true);
						cause.sendMessage(TranslationHelper.t("text.player.attacker", cause.getLocale()));
						cause.sendMessage(TranslationHelper.t("text.player.toggle", cause.getLocale()));
						return;
					}
				} else if (entityTarget.getType() == EntityTypes.PLAYER) {
					Player target = (Player) entityTarget;
					if (!node.getNode("players", target.getUniqueId(), "pvp").getBoolean()) {
						event.setCancelled(true);
						cause.sendMessage(TranslationHelper.t("text.player.target", cause.getLocale()));
						target.sendMessage(TranslationHelper.t("text.player.attacked", cause.getLocale()));
						return;
					}
				}
/*			} else if (source.getSource().getType() == EntityTypes.ARROW) {
				IndirectEntityDamageSource arrowSource = event.getCause().last(IndirectEntityDamageSource.class).get();
				if (arrowSource.getIndirectSource().getType() == EntityTypes.PLAYER) {
					Player cause = (Player) arrowSource.getIndirectSource();
				if (!node.getNode("players", cause.getUniqueId(), "pvp").getBoolean()) {
					if (entityTarget.getType() == EntityTypes.PLAYER) {
						event.setCancelled(true);
						cause.sendMessage(TranslationHelper.t("text.player.attacker", cause.getLocale()));
						cause.sendMessage(TranslationHelper.t("text.player.toggle", cause.getLocale()));
						return;
					}
				} else if (entityTarget.getType() == EntityTypes.PLAYER) {
					Player target = (Player) entityTarget;
					if (!node.getNode("players", target.getUniqueId(), "pvp").getBoolean()) {
							event.setCancelled(true);
							cause.sendMessage(TranslationHelper.t("text.player.target", cause.getLocale()));
							target.sendMessage(TranslationHelper.t("text.player.attacked", cause.getLocale()));
							return;
					}
				}
			} */
		}
	}
}
