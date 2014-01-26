package net.roguedraco.nomorehunger;


import org.bukkit.ChatColor;
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
			if(!player.hasPermission("nomorehunger.exclude") || player.hasPermission("nomorehunger.include")) {
				event.setCancelled(true);
				player.setFoodLevel(20); // Just incase
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(NoMoreHungerPlugin.getUpdater() != null) {
			if(event.getPlayer().hasPermission("nomorehunger.update")) {
				Updater updater = NoMoreHungerPlugin.getUpdater();
				
				if(updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE) {
					event.getPlayer().sendMessage(ChatColor.GREEN+"An update is available for NoMoreHunger!");
					event.getPlayer().sendMessage(ChatColor.GREEN+"Latest Version: " + updater.getLatestName() + " " + updater.getLatestFileLink());
				}
			}
		}
			
	}
}
