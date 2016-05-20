# PvPToggle

PvPToggle is a simple plugin that adds only one functionality: the ability for players to toggle their own PvP status. That means that any player with their own PvP status disabled cannot be attacked or participate in PvP with other players. To control this, one simple command and permission node is added:

`/pvp - pvptoggle.toggle - Toggles the PvP status of the player.`

To toggle other players' PvP status, this command can also be run from the server console, a command block, or any server admin that has the associated permission node:

`/pvp [<player>] - pvptoggle.others - Toggles the PvP status of the indicated player.`

Downloads can be found on Ore.

This is my first plugin, so I am not very familiar with the SpongeAPI, and it's been a while since I've worked with actual Java code. Please feel free to provide any feedback, report issues, suggest changes in the code, as well as recommend new features.

## Current Features (On Latest Release)

* Adds a /pvp command and permission node to toggle PvP status.
* Allows for other players' PvP status to be toggled.
* Notifies players if PvP is disabled or if a player cannot be attacked.
* Listens for movement to prevent players from toggling PvP in a combat situation.
* Supports translations!

### Translations

The code for PvPToggle now uses the ResourceBundle system, allowing for the implementation of translations. Translators are requested for this project, and would be greatly appreciated. If you are interested in translating the plugin to another language, the file with all translatable messages can be found [here.](https://github.com/TehTotalPwnage/PvPToggle/blob/master/src/main/resources/io/tehtotalpwnage/pvptoggle/lang/Messages.properties) From there, open an issue on my GitHub and I'll get around to looking at it.