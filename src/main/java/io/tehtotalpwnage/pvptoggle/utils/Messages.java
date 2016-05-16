package io.tehtotalpwnage.pvptoggle.utils;

import java.util.ListResourceBundle;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Messages extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = {
		{ "attacked" , Text.builder("A player attempted to attack you!").color(TextColors.RED).build() },
		{ "attacker" , Text.builder("You cannot attack players while you have PvP disabled!")
			.color(TextColors.RED).build() },
		{ "disabled" , Text.builder("PvP is now ").append(
			Text.builder("disabled.").color(TextColors.GREEN).build()).build() },
		{ "enabled" , Text.builder("PvP is now ").append(
			Text.builder("enabled.").color(TextColors.RED).build()).build() },
		{ "command" , Text.of("Toggling PvP. Please remain still for 10 seconds.") },
		{ "commandCancelled" , Text.builder("You moved! Command has been cancelled and PvP has not been toggled.") 
			.color(TextColors.RED).build() },
		{ "target" , Text.builder("This player has PvP disabled!").color(TextColors.RED).build() },
		{ "toggle" , Text.builder("To toggle PvP status, run the command: ").append(
			Text.builder("/pvp").color(TextColors.AQUA).build()).build() }
	};
}
