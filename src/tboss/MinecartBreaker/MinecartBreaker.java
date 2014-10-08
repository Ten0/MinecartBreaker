package tboss.MinecartBreaker;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class MinecartBreaker extends JavaPlugin implements Listener {
	
	private static final Logger log = Logger.getLogger("Minecraft");
	private static Vehicle vehicle = null;

	public void onDisable() {
		log.info("MinecartBreaker DISABLED");
	}
	
	public void onEnable()
	{
		getServer().getPluginManager().registerEvents(this, this);
		log.info("MinecartBreaker ENABLED");
	}
	
	@EventHandler
	public void onVehicleExit(VehicleExitEvent event) {
		if (event.getVehicle() instanceof Minecart) {
			vehicle = event.getVehicle();
			Location loc = vehicle.getLocation();
			vehicle.remove();
			loc.getWorld().dropItem(loc, new ItemStack(Material.MINECART, 1));
		}
	}
	
	@EventHandler
	public void onVehicleDestroy(VehicleDestroyEvent event) {
		if (vehicle.equals(event.getVehicle()))
			event.setCancelled(true);
	}
}
