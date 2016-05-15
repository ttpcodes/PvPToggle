package io.tehtotalpwnage.pvptoggle.utils;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Messages {
	
	private static Messages messages = new Messages();
	public static Messages getMessages() {
		return messages;
	}
	
	public static Text attacked = Text.builder("A player attempted to attack you!").color(TextColors.RED).build();
	public static Text attacker = Text.builder("You cannot attack players while you have PvP disabled!")
		.color(TextColors.RED).build();
	public static Text disabled = Text.builder("PvP is currently ").append(
		Text.builder("disabled.").color(TextColors.GREEN).build()).build();
	public static Text enabled = Text.builder("PvP is currently ").append(
		Text.builder("enabled.").color(TextColors.RED).build()).build();
	public static Text command = Text.of("Toggling PvP. Please remain still for 10 seconds.");
	public static Text commandCancelled = Text.builder("You moved! Command has been cancelled and PvP has not been toggled.")
		.color(TextColors.RED).build();
	public static Text target = Text.builder("This player has PvP disabled!").color(TextColors.RED).build();
	public static Text toggle = Text.builder("To toggle PvP status, run the command: ").append(
		Text.builder("/pvp").color(TextColors.AQUA).build()).build();
}
