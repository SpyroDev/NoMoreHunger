package net.roguedraco.nomorehunger;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {
	
	@EventHandler
	public void onHunger(FoodLevelChangeEvent event) {
		if(event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if(!NoMoreHungerPlugin.permission.playerHas(player, "nomorehunger.exclude")) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(NoMoreHungerPlugin.permission.playerHas(event.getPlayer(), "nomorehunger.update")) {
			NoMoreHungerPlugin.getUpdater().updateNeeded(event.getPlayer());
		}
	}
}
