package io.tehtotalpwnage.pvptoggle.utils;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import ninja.leaping.configurate.ConfigurationNode;

public class CommandToggle implements CommandExecutor {
	
	Messages messages = Messages.getMessages();
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		if (src instanceof Player) {
			Player player = (Player) src;
			ConfigurationNode node = PlayerList.getPlayerList().getNode().getNode(
					"players", player.getUniqueId(), "pvp");
			if (node.getBoolean()) {
				node.setValue(false);
				PlayerList.getPlayerList().save();
				player.sendMessage(messages.disabled);
				player.sendMessage(messages.toggle);
			} else {
				node.setValue(true);
				PlayerList.getPlayerList().save();
				player.sendMessage(messages.enabled);
				player.sendMessage(messages.toggle);
			}
		}
		else {
			src.sendMessage(Text.of("This command must be run by a player!"));
		}
		return CommandResult.success();
	}
}
