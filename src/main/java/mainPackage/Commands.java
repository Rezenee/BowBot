package mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import io.netty.util.internal.ThreadLocalRandom;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.CommandExecute;

public class Commands extends CommandExecute implements Listener, CommandExecutor{

	public String start = "start";
	public String botAdd = "botadd";
	public String botRemove = "botremove";
	public String botSet = "botset";
	public String stop = "stop";
	int botCount = 10;

	public void spawn() {
		Bukkit.broadcastMessage("Spawn mob");
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// TODO Auto-generated method stub
		
		if(cmd.getName().equalsIgnoreCase(botRemove)) {
			if(botCount > 0) {
				botCount--;
				String msg = Integer.toString(botCount);
				Bukkit.broadcastMessage(ChatColor.GOLD + "You have " + msg + " bots spawned.");
				return true;
			}
			else {
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "You cannot have less than 0 bots.");
				return true;
			}
		}

		if(cmd.getName().equalsIgnoreCase(botAdd)) {
			if(botCount < 10) {
				botCount++;
				String msg = Integer.toString(botCount);
				Bukkit.broadcastMessage(ChatColor.GOLD + "You have " + msg + " bots spawned.");
				return true;
			}
			else {
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "You cannot have more than 10 bots.");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase(botSet)) {
			if(args.length == 1) {
				int argAmount = Integer.parseInt(args[0]);
				if(argAmount > 10) {
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "You cannot have more than 10 bots.");
					return true;
				}
				if(argAmount < 0) {
					Bukkit.broadcastMessage(ChatColor.DARK_RED + "You cannot have less than 0 bots.");
					return true;
				}
				botCount = argAmount;
				Bukkit.broadcastMessage(ChatColor.GOLD + "You have set the bot count to " + botCount + " bots.");
				return true;
			}
			else {
				Bukkit.broadcastMessage(ChatColor.DARK_RED + "Incorrect Formatting \n" + ChatColor.DARK_PURPLE + "Command Usage: /botSet $AMOUNTOFBOTS");
				return true;
			}
		}
		
		if(cmd.getName().equalsIgnoreCase(start)) {
			Player player = (Player) sender;
			if(args.length != 1) {
				player.sendMessage("You must have one argument.");
			}
			else {
				if(args[0] == "flickclose") {
					int min = 0;
					int max = botCount;
					for(int i=0;i<max;i++) {
						NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "");
						int random_x = ThreadLocalRandom.current().nextInt(-8, 7 + 1);
						int random_z = ThreadLocalRandom.current().nextInt(-25, -8 + 1);
						int y = 52;
						Location loc = new Location(Bukkit.getWorld("world"), random_x, y, random_z);
						npc.spawn(loc);
						npc.setProtected(false);
					}
					return true;
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase(stop)) {
			CitizensAPI.getNPCRegistry().deregisterAll();
			return true;
		}
		return false;
	}

}
				

