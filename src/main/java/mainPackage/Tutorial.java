package mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public class Tutorial extends JavaPlugin implements Listener{
	
	public Commands commands = new Commands(); 
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_GREEN + "Test");
		getServer().getPluginManager().registerEvents(new Damaged(), this);
		getCommand(commands.start).setExecutor(commands);
		getCommand(commands.botAdd).setExecutor(commands);
		getCommand(commands.botRemove).setExecutor(commands);
		getCommand(commands.botSet).setExecutor(commands);
		getCommand(commands.stop).setExecutor(commands);
	}
	
	public void onDisable() {
		Bukkit.broadcastMessage(ChatColor.DARK_RED + "SHUTDOWN:::::::");
	}
}
