package mainPackage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import io.netty.util.internal.ThreadLocalRandom;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;

public class Damaged implements Listener{

	@EventHandler
	public void onDamageEvent(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		if(p instanceof Player) {
			if(e.getCause() == DamageCause.PROJECTILE) {
				p.remove();
				NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "")
;
				int random_x = ThreadLocalRandom.current().nextInt(-8, 7 + 1);
				int random_z = ThreadLocalRandom.current().nextInt(-25, -8 + 1);
				int y = 52;
				Location loc = new Location(Bukkit.getWorld("world"), random_x, y, random_z);
				npc.spawn(loc);
				npc.setProtected(false);
			}
		}
	}
}
