/* package io.tehtotalpwnage.pvptoggle.lang;

import java.util.ListResourceBundle;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Messages extends ListResourceBundle {
	
	@Override
	protected Object[][] getContents() {
		return contents;
	}

	private Object[][] contents = {
		{ "messages.player.attacked" , Text.builder("A player attempted to attack you!").color(TextColors.RED).build() },
		{ "messages.player.attacker" , Text.builder("You cannot attack players while you have PvP disabled!")
			.color(TextColors.RED).build() },
		{ "messages.player.disabled" , Text.builder("PvP is now ").append(
			Text.builder("disabled.").color(TextColors.GREEN).build()).build() },
		{ "messages.player.enabled" , Text.builder("PvP is now ").append(
			Text.builder("enabled.").color(TextColors.RED).build()).build() },
		{ "messages.player.command" , Text.of("Toggling PvP. Please remain still for 10 seconds.") },
		{ "messages.player.commandCancelled" , Text.builder("You moved! Command has been cancelled and PvP has not been toggled.") 
			.color(TextColors.RED).build() },
		{ "messages.player.target" , Text.builder("This player has PvP disabled!").color(TextColors.RED).build() },
		{ "messages.player.toggle" , Text.builder("To toggle PvP status, run the command: ").append(
			Text.builder("/pvp").color(TextColors.AQUA).build()).build() }
	};
} */