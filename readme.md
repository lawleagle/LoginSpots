How to use
--
/loginspots add <br>
adds invoking player's current position as a login spot

/loginspots clear <br>
removes all login spots

/loginspots tp <br>
teleports invoking player to his assigned login spot (if assigned)

/loginspots list <br>
lists all currently existing login spots

/loginspots set-min-y <min-y> <br>
sets the min-y <br>
if the player's position is at any tick below the min-y, the player will be teleported to his assigned login spot (if he has one)
if it's used from the console, min-y is a mandatory argument
if it's a player, min-y is is optional, if it is missing, the