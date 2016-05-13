package io.tehtotalpwnage.pvptoggle.utils;

import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class Messages {
	
	private static Messages messages = new Messages();
	public static Messages getMessages() {
		return messages;
	}
	
	public Text attacked = Text.builder("A player attempted to attack you!").color(TextColors.RED).build();
	public Text attacker = Text.builder("You cannot attack players while you have PvP disabled!")
			.color(TextColors.RED).build();
	public Text disabled = Text.builder("PvP is currently ").append(
		Text.builder("disabled.").color(TextColors.GREEN).build()).build();
	public Text enabled = Text.builder("PvP is currently ").append(
			Text.builder("enabled.").color(TextColors.RED).build()).build();
	public Text target = Text.builder("This player has PvP disabled!").color(TextColors.RED).build();
	public Text toggle = Text.builder("To toggle PvP status, run the command: ").append(
		Text.builder("/pvp").color(TextColors.AQUA).build()).build();
}
