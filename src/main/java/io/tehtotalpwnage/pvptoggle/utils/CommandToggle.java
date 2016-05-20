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
import org.spongepowered.api.text.Text;

import com.google.common.collect.ImmutableMap;

import io.tehtotalpwnage.pvptoggle.PvPToggle;
import io.tehtotalpwnage.pvptoggle.configs.PlayerList;
import ninja.leaping.configurate.ConfigurationNode;

public class CommandToggle implements CommandExecutor {
	
	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
		Locale locale = PvPToggle.locale;
		Optional<Player> optionalPlayer = args.<Player> getOne("player");
		if (src instanceof Player) {
			Player player = (Player) src;
			if (optionalPlayer.isPresent() && src.hasPermission("pvp.toggle.others")) {
				Player target = optionalPlayer.get();
				toggleOthers(target);
				player.sendMessage(TranslationHelper.tt("textTemplate.generic.toggle", locale)
					.apply(ImmutableMap.of("player", Text.of(target.getName()))).build());
			} else if (!optionalPlayer.isPresent()) {
				PvPToggle.togglePlayers.add(player.getUniqueId());
				player.sendMessage(TranslationHelper.t("text.player.command", player.getLocale()));
				ConfigurationNode node = PlayerList.getInstance().getNode()
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
				throw new CommandException(TranslationHelper.t("text.player.permission", player.getLocale()));
			}
		} else {
			if (optionalPlayer.isPresent()) {
				Player target = optionalPlayer.get();
				toggleOthers(target);
				src.sendMessage(TranslationHelper.tt("textTemplate.generic.toggle", locale)
					.apply(ImmutableMap.of("player", Text.of(target.getName()))).build());
			} else {
				throw new CommandException(TranslationHelper.t("text.console.missingPlayer", locale));
			}
		}
		return CommandResult.success();
	}
	
	public void toggleOthers(Player target) {
		ConfigurationNode node = PlayerList.getInstance().getNode()
				.getNode("players", target.getUniqueId(), "pvp");
			if (node.getBoolean()) {
				node.setValue(false);
				PlayerList.getInstance().save();
				target.sendMessage(TranslationHelper.t("text.player.console", target.getLocale()));
				target.sendMessage(TranslationHelper.t("text.player.disabled", target.getLocale()));
				target.sendMessage(TranslationHelper.t("text.player.toggle", target.getLocale()));
			} else {
				node.setValue(true);
				PlayerList.getInstance().save();
				target.sendMessage(TranslationHelper.t("text.player.console", target.getLocale()));
				target.sendMessage(TranslationHelper.t("text.player.enabled", target.getLocale()));
				target.sendMessage(TranslationHelper.t("text.player.toggle", target.getLocale()));
			}
	}
}