# LobbyProtection
Simple server protection plugin with complete config control. Use /build to bypass all protections.

SpigotMC: https://www.spigotmc.org/resources/lobbyprotection.99483/

This simple plugin allows you to stop players from: 
- placing blocks 
- breaking blocks 
- dropping items 
- picking up items 
- changing up their inventory 
- taking damage 
- getting hungry 

additional features: 

- toggle if all players can take damage (by command /dmg)
- disable player join message
- disable player quit message
- disable player kick message
- clear inventory on join
- clear armor on join
- reset xp level on join
- set the weather to clear on server start
- set gamerule doDaylightCycle to false on server start
- set the time to noon (12000) on server start
- set gamerule keepInventory to true on server start

You can disable every feature in the config file. Just change true to false.
On join, every players gamemode gets changed to survival mode.

Commands: 
- /build 
- permission: build.buildCommand 
- usage: /build 
- Allows just the player who executed the command to bypass all restrictions. Also the gamemode of the player gets changed to creative mode. All other players are unaffected. 
- /dmg 
- permission: build.dmgCommand 
- usage: /dmg 
- Toggles by ingame command if all players can take damage or not. Returns a message with current state: "Player damage is now true/false" 


- config.yml
disablePlayerDamage: true
disableInventoryClickEvent: true
disableBlockBreak: true
disableBlockPlace: true
disablePlayerDropItem: true
disablePlayerPickupItem: true
disableFoodLevelChange: true
disablePlayerJoinMessage: true
disablePlayerQuitMessage: true
disablePlayerKickMessage: true
clearInventoryOnJoin: true
clearArmorOnJoin: true
xpLevelResetOnJoin: true
setWeatherToClearOnStart: true
disableDaylightCycle: true
setTimeOnStart: true
time: 12000
keepInventory: true
