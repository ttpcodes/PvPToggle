package io.tehtotalpwnage.pvptoggle.lang;

import java.util.ListResourceBundle;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.TextTemplate;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import static org.spongepowered.api.text.TextTemplate.*;

public class Locale extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}
	private Object[][] contents = {
		{ "string.console.command" , "Successfully registered commands." },
		{ "string.console.commandError" , "Error on registering commands: %s" },
		{ "string.console.createConfig" , "%s doesn't exist. Creating config..." },
		{ "string.console.createdConfig" , "Created new %s in config directory." },
		{ "string.console.createConfigDir", "Config directory doesn't exist. Creating config directory..." },
		{ "string.console.createdConfigDir", "Created config directory at %s" },
		{ "string.console.errorConfigDir" , "Error occured on creating config directory: %s" },
		{ "string.console.errorCreateConfig" , "Error occured on creating config %1$s: %2$s" },
		{ "string.console.init" , "Loading PvPToggle version %s" },
		{ "string.console.initComplete" , "PvPToggle version %s loaded successfully." },
		{ "string.console.listener" , "Successfully registered listeners." },
		{ "string.console.listenerError" , "Error on registering listeners: %s"},
		{ "string.console.load" , "Loaded config %s." },
		{ "string.console.loadError" , "Error occured on loading config %1$s: %2$s" },
		{ "string.console.populate" , "Populated %s with initial config." },
		{ "string.console.preinit" , "Running preloading of PvPToggle version %s" },
		{ "string.console.preinitComplete" , "Preloading completed." },
		{ "string.console.save" , "Config %s saved." },
		{ "string.console.saveError" , "Error occured on saving config %1$s: %2$s" },
		{ "text.player.attacked" , Text.builder("A player attempted to attack you!").color(TextColors.RED).build() },
		{ "text.player.attacker" , Text.builder("You cannot attack players while you have PvP disabled!")
			.color(TextColors.RED).build() },
		{ "text.player.console" , Text.builder("A server admin has toggled your PvP status!").color(TextColors.RED).build() },
		{ "text.player.disabled" , Text.builder("PvP is now ").append(
			Text.builder("disabled.").color(TextColors.GREEN).build()).build() },
		{ "text.player.enabled" , Text.builder("PvP is now ").append(
			Text.builder("enabled.").color(TextColors.RED).build()).build() },
		{ "text.player.command" , Text.of("Toggling PvP. Please remain still for 10 seconds.") },
		{ "text.player.commandCancelled" , Text.builder("You moved! Command has been cancelled and PvP has not been toggled.") 
			.color(TextColors.RED).build() },
		{ "text.player.permission" , Text.builder("You do not have permission to toggle other players' PvP status!")
			.color(TextColors.RED).build() },
		{ "text.player.target" , Text.builder("This player has PvP disabled!").color(TextColors.RED).build() },
		{ "text.player.toggle" , Text.builder("To toggle PvP status, run the command: ").append(
			Text.builder("/pvp").color(TextColors.AQUA).build()).build() },
		{ "textTemplate.generic.toggle" , TextTemplate.of(TextColors.GREEN, TextStyles.NONE, "Successfully toggled PvP status of player ", 
		arg("player").color(TextColors.AQUA).style(TextStyles.NONE), ".") }
	};
}