package io.tehtotalpwnage.pvptoggle.utils;

import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.configs.Config;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import ninja.leaping.configurate.ConfigurationNode;

public class CommandToggle implements CommandExecutor {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		ConfigurationNode node;
		if (src instanceof Player) {
			Player player = (Player) src;
			PvPToggle.togglePlayers.add(player.getUniqueId());
			player.sendMessage(TranslationHelper.t("text.player.command", player.getLocale()));
			node = PlayerList.getInstance().getNode()
				.getNode("players", player.getUniqueId(), "pvp");
			Sponge.getScheduler().createTaskBuilder().execute(() -> {
					if (PvPToggle.togglePlayers.contains(player.getUniqueId())) {
						PvPToggle.togglePlayers.remove(player.getUniqueId());
						if (node.getBoolean()) {
							node.setValue(false);
							PlayerList.getInstance().save();
							player.sendMessage(TranslationHelper.t("text.player.disabled", player.getLocale()));
							player.sendMessage(TranslationHelper.t("text.player.toggle", player.getLocale()));
						} else {
							node.setValue(true);
							PlayerList.getInstance().save();
							player.sendMessage(TranslationHelper.t("text.player.enabled", player.getLocale()));
							player.sendMessage(TranslationHelper.t("text.player.toggle", player.getLocale()));
						}
					}
				}).delay(10, TimeUnit.SECONDS).name("PvPToggle - Toggle PvP Status")
				.submit(Sponge.getPluginManager().getPlugin("io.tehtotalpwnage.pvptoggle")
				.get().getInstance().get());
		} else {
			Locale locale = new Locale(Config.getInstance().getNode().getNode("locale").getValue().toString());
			Optional<Player> optionalPlayer = args.<Player> getOne("player");
			if (optionalPlayer.isPresent()) {
				Player player = optionalPlayer.get();
				node = PlayerList.getInstance().getNode()
					.getNode("players", player.getUniqueId(), "pvp");
				if (node.getBoolean()) {
					node.setValue(false);
					PlayerList.getInstance().save();
					player.sendMessage(TranslationHelper.t("text.player.console", player.getLocale()));
					player.sendMessage(TranslationHelper.t("text.player.disabled", player.getLocale()));
					player.sendMessage(TranslationHelper.t("text.player.toggle", player.getLocale()));
				} else {
					node.setValue(true);
					PlayerList.getInstance().save();
					player.sendMessage(TranslationHelper.t("text.player.console", player.getLocale()));
					player.sendMessage(TranslationHelper.t("text.player.enabled", player.getLocale()));
					player.sendMessage(TranslationHelper.t("text.player.toggle", player.getLocale()));
				}
				return CommandResult.success();
			} else {
				throw new CommandException(TranslationHelper.t("text.console.missingPlayer", locale));
			}
		}
		return CommandResult.success();
	}
}