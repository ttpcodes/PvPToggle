package io.tehtotalpwnage.pvptoggle.utils;

import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import ninja.leaping.configurate.ConfigurationNode;

public class CommandToggle implements CommandExecutor {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player player = (Player) src;
			PvPToggle.togglePlayers.add(player.getUniqueId());
			player.sendMessage(Messages.command);
			ConfigurationNode node = PlayerList.getPlayerList().getNode()
				.getNode("players", player.getUniqueId(), "pvp");
			Sponge.getScheduler().createTaskBuilder().execute(() -> {
					if (PvPToggle.togglePlayers.contains(player.getUniqueId())) {
						PvPToggle.togglePlayers.remove(player.getUniqueId());
						if (node.getNode("players", player.getUniqueId(), "pvp").getBoolean()) {
							node.getNode("players", player.getUniqueId(), "pvp").setValue(false);
							PlayerList.getPlayerList().save();
							player.sendMessage(Messages.disabled);
							player.sendMessage(Messages.toggle);
						} else {
							node.getNode("players", player.getUniqueId(), "pvp").setValue(true);
							PlayerList.getPlayerList().save();
							player.sendMessage(Messages.enabled);
							player.sendMessage(Messages.toggle);
						}
					}
				}).delay(10, TimeUnit.SECONDS).name("PvPToggle - Toggle PvP Status")
				.submit(Sponge.getPluginManager().getPlugin("io.tehtotalpwnage.pvptoggle")
				.get().getInstance().get());
		} else {
			src.sendMessage(Text.of("This command must be run by a player!"));
			return CommandResult.success();
		}
		return CommandResult.success();
	}
}