package io.tehtotalpwnage.pvptoggle.listeners;

import java.util.Optional;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.entity.damage.source.EntityDamageSource;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import io.tehtotalpwnage.pvptoggle.utils.Messages;
import ninja.leaping.configurate.ConfigurationNode;

public class PvPListener {
	
	@Listener
	public void onDamageEntity(DamageEntityEvent event) {
		ConfigurationNode node = PlayerList.getPlayerList().getNode();
		Entity entityTarget = event.getTargetEntity();
		Optional<EntityDamageSource> optionalCause = event.getCause().first(EntityDamageSource.class);
		EntityDamageSource source;
		if (optionalCause.isPresent()) {
			source =  optionalCause.get();
		} else {
			return;
		}
		if (source.getSource().getType() == EntityTypes.PLAYER) {
			Player cause = (Player) source.getSource();
			if (!node.getNode("players", cause.getUniqueId(), "pvp").getBoolean()) {
				if (entityTarget.getType() == EntityTypes.PLAYER) {
					event.setCancelled(true);
					cause.sendMessage(Messages.attacker);
					cause.sendMessage(Messages.toggle);
					return;
				}
			} else if (entityTarget.getType() == EntityTypes.PLAYER) {
				Player target = (Player) entityTarget;
				if (!node.getNode("players", target.getUniqueId(), "pvp").getBoolean()) {
						event.setCancelled(true);
						cause.sendMessage(Messages.target);
						target.sendMessage(Messages.attacked);
						return;
				}
			}
		}
	}
}
