# Changelog

## [1.8.9-1] - 6/19/2016
**Release Type: Release**

**Tested on SpongeForge Build 1315**
- New version numbering system.
- **BUGFIX: Fixed an issue where arrow damage was not cancelled.**

## [1.1.1] - 5/19/2016
**Release Type: Release**

**Tested on SpongeForge Build 1315**
- **HOTFIX: Fixed an incorrectly named permission node.**

## [1.1] - 5/19/2016
**Release Type: Release**

**Tested on SpongeForge Build 1315**
- Finished conversion of Texts and Strings to ResourceBundle system.
- Shortened PvP movement delay to 500 milliseconds from 1 second.
- Implemented TextTemplates and arguments of Texts.

## [1.0.4] - 5/18/2016
**Release Type: Beta**

**Tested on SpongeForge Build 1315**
- Minor reorganization of logging.
- Began conversion of Texts and Strings to ResourceBundle system.
- Combined similar configuration loading functions into one ConfigHelper class.
- Added locale support for logging.
- Conversion to CommentedConfigurationNode from ConfigurationNodes.
- Reorganized translator functions and keys.
- Added string support for arguments.

## [1.0.3] - 5/17/2016
**Release Type: Beta**

**Tested on SpongeForge Build 1315**
- Updated README.md on GitHub repository.
- Reorganized of the main class file.
- Finally fixed all getter and setter references.
- Added new primary configuration file.
- Optimized player list loading process.
- Reimplemented Text to chat messages in ResourceBundles.
- Implemented client side localization support.

## [1.0.2] - 5/16/2016
**Release Type: Beta**

**Tested on  SpongeForge Build 1315**
- Moved plugin information to separate PluginInfo class file.
- Added player argument for toggling PvP from console or command block.
- Changed messages system to use ResourceBundle API.
- Minor optimization to Entity Damage listener **(Thanks pie_flavor on the SpongeForge forums.)**
- Added translation helper to support translations **(Not fully implemented.)**
- **REGRESSION: Messages no longer have chat colors.**

## [1.0.1] - 5/14/2016
**Release Type: Beta**

**Tested on SpongeForge Build 1315**
- Removed unnecessary getter references for chat messages.
- Added movement listener to prevent toggling of PvP in combat situations.

## [1.0] - 5/12/2016
**Release Type: Release**

**Tested on SpongeForge Build 1315**
- First release!
- Added /pvp command to toggle PvP.
- Added listeners to cancel PvP events and notify players about PvP status.

 