package io.tehtotalpwnage.pvptoggle.listeners;

import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DisplaceEntityEvent;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.utils.TranslationHelper;

public class MovementListener {

	@Listener
	public void onDisplaceEntity(DisplaceEntityEvent.Move.TargetPlayer event) {
		Player player = event.getTargetEntity();
		Location<World> location = player.getLocation();
		Sponge.getScheduler().createTaskBuilder().execute(() -> {
				if (PvPToggle.togglePlayers.contains(player.getUniqueId())) {
					Location<World> newLocation = player.getLocation();
					if (location.getX() != newLocation.getX() 
						|| location.getY() != newLocation.getY() 
						|| location.getZ() != newLocation.getZ()) {
							PvPToggle.togglePlayers.remove(player.getUniqueId());
							player.sendMessage(TranslationHelper.t("messages.player.commandCancelled", player.getLocale()));
					}
				}
			}).delay(1, TimeUnit.SECONDS).name("PvPToggle - Get Movement Change")
			.submit(Sponge.getPluginManager().getPlugin("io.tehtotalpwnage.pvptoggle").get().getInstance().get());
	}
}