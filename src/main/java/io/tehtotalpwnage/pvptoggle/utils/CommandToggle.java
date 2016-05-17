package io.tehtotalpwnage.pvptoggle.utils;

import java.util.Optional;
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
		ConfigurationNode node;
		if (src instanceof Player) {
			Player player = (Player) src;
			PvPToggle.togglePlayers.add(player.getUniqueId());
			player.sendMessage(TranslationHelper.t("messages.player.command", player.getLocale()));
			node = PlayerList.getPlayerList().getNode()
				.getNode("players", player.getUniqueId(), "pvp");
			Sponge.getScheduler().createTaskBuilder().execute(() -> {
					if (PvPToggle.togglePlayers.contains(player.getUniqueId())) {
						PvPToggle.togglePlayers.remove(player.getUniqueId());
						if (node.getBoolean()) {
							node.setValue(false);
							PlayerList.getPlayerList().save();
							player.sendMessage(TranslationHelper.t("messages.player.disabled", player.getLocale()));
							player.sendMessage(TranslationHelper.t("messages.player.toggle", player.getLocale()));
						} else {
							node.setValue(true);
							PlayerList.getPlayerList().save();
							player.sendMessage(TranslationHelper.t("messages.player.enabled", player.getLocale()));
							player.sendMessage(TranslationHelper.t("messages.player.toggle", player.getLocale()));
						}
					}
				}).delay(10, TimeUnit.SECONDS).name("PvPToggle - Toggle PvP Status")
				.submit(Sponge.getPluginManager().getPlugin("io.tehtotalpwnage.pvptoggle")
				.get().getInstance().get());
		} else {
			Optional<Player> optionalPlayer = args.<Player> getOne("player");
			if (optionalPlayer.isPresent()) {
				Player player = optionalPlayer.get();
				node = PlayerList.getPlayerList().getNode()
					.getNode("players", player.getUniqueId(), "pvp");
				if (node.getBoolean()) {
					node.setValue(false);
					PlayerList.getPlayerList().save();
					player.sendMessage(TranslationHelper.t("messages.player.console", player.getLocale()));
					player.sendMessage(TranslationHelper.t("messages.player.disabled", player.getLocale()));
					player.sendMessage(TranslationHelper.t("messages.player.toggle", player.getLocale()));
				} else {
					node.setValue(true);
					PlayerList.getPlayerList().save();
					player.sendMessage(TranslationHelper.t("messages.player.console", player.getLocale()));
					player.sendMessage(TranslationHelper.t("messages.player.enabled", player.getLocale()));
					player.sendMessage(TranslationHelper.t("messages.player.toggle", player.getLocale()));
				}
				return CommandResult.success();
			} else {
				throw new CommandException(Text.of("A player argument is required when running from console or command block!"));
			}
		}
		return CommandResult.success();
	}
}