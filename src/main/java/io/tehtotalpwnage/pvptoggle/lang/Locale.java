package io.tehtotalpwnage.pvptoggle.lang;

import java.util.ListResourceBundle;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Locale extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}
	private Object[][] contents = {
		{ "string.console.createConfig" , "%s doesn't exist. Creating config..." },
		{ "string.console.createdConfig" , "Created new %s in config directory." },
		{ "string.console.createConfigDir", "Config directory doesn't exist. Creating config directory..." },
		{ "string.console.createdConfigDir", "Created config directory at %s" },
		{ "string.console.errorConfigDir" , "Error occured on creating config directory: %s" },
		{ "string.console.init" , "Loading PvPToggle version %s" },
		{ "string.console.initComplete" , "PvPToggle version %s loaded successfully." },
		{ "string.console.preinit" , "Running preloading of PvPToggle version 1.0" },
		{ "string.console.preinitComplete" , "Preloading completed." },
		{ "text.player.attacked" , Text.builder("A player attempted to attack you!").color(TextColors.RED).build() },
		{ "text.player.attacker" , Text.builder("You cannot attack players while you have PvP disabled!")
			.color(TextColors.RED).build() },
		{ "text.player.disabled" , Text.builder("PvP is now ").append(
			Text.builder("disabled.").color(TextColors.GREEN).build()).build() },
		{ "text.player.enabled" , Text.builder("PvP is now ").append(
			Text.builder("enabled.").color(TextColors.RED).build()).build() },
		{ "text.player.command" , Text.of("Toggling PvP. Please remain still for 10 seconds.") },
		{ "text.player.commandCancelled" , Text.builder("You moved! Command has been cancelled and PvP has not been toggled.") 
			.color(TextColors.RED).build() },
		{ "text.player.target" , Text.builder("This player has PvP disabled!").color(TextColors.RED).build() },
		{ "text.player.toggle" , Text.builder("To toggle PvP status, run the command: ").append(
			Text.builder("/pvp").color(TextColors.AQUA).build()).build() }
	};
}