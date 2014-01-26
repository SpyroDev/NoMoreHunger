package net.roguedraco.nomorehunger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoMoreHungerPlugin extends JavaPlugin {
	public static Logger logger;
	
	public static String pluginName;
	public static String pluginVersion;
	
	public static NoMoreHungerPlugin plugin;

	public static Lang lang;
	
	private static Updater updater;

	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		NoMoreHungerPlugin.logger = Logger.getLogger("Minecraft");
		NoMoreHungerPlugin.plugin = this;
		NoMoreHungerPlugin.pluginName = this.getDescription().getName();
		NoMoreHungerPlugin.pluginVersion = this.getDescription().getVersion();
		
		NoMoreHungerPlugin.lang = new Lang(this);
		lang.setupLanguage();
		
		PluginManager pm = getServer().getPluginManager();
		Listener events = new Events();
		pm.registerEvents(events, this);
		
		if(getConfig().getBoolean("update-check",true))
			updater = new Updater(this, 42354, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
		
		log(Lang.get("plugin.enabled"));
	}

	public void onDisable() {
		log(Lang.get("plugin.disabled"));
	}

	public static void log(String message) {
		log(Level.INFO, message);
	}

	public static void log(Level level, String message) {
		if (plugin.getConfig().getBoolean("useFancyConsole") == true
				&& level == Level.INFO) {
			ConsoleCommandSender console = Bukkit.getServer()
					.getConsoleSender();
			console.sendMessage("[" + ChatColor.LIGHT_PURPLE + pluginName
					+ " v" + pluginVersion + ChatColor.GRAY + "] " + message);
		} else {
			NoMoreHungerPlugin.logger.log(level, "[" + pluginName + " v"
					+ pluginVersion + "] " + message);
		}
	}

	public static void debug(String message) {
		if (plugin.getConfig().getBoolean("debug")) {
			if (plugin.getConfig().getBoolean("useFancyConsole") == true) {
				ConsoleCommandSender console = Bukkit.getServer()
						.getConsoleSender();
				console.sendMessage("[" + ChatColor.LIGHT_PURPLE + pluginName
						+ " v" + pluginVersion + " Debug" + ChatColor.GRAY
						+ "] " + message);
			} else {
				System.out.println("[" + pluginName + " v" + pluginVersion
						+ " Debug" + "] " + message);
			}
		}
	}

	public static Updater getUpdater() {
		return updater;
	}
}
