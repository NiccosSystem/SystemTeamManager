package net.niccossystem.systemteammanager.scatter;

import java.util.ArrayList;
import java.util.Random;
import org.bukkit.Location;
import org.bukkit.Material;

public class TeamScatter {
    private static Random ranGen = new Random();
    private final static ArrayList<Material> acceptedBlocks = new ArrayList<Material>();
    
    static {
        acceptedBlocks.add(Material.DIRT);
        acceptedBlocks.add(Material.GRASS);
        acceptedBlocks.add(Material.SAND);
        acceptedBlocks.add(Material.STONE);
        acceptedBlocks.add(Material.SNOW);
        acceptedBlocks.add(Material.DEAD_BUSH);
    }    
    
    public static ArrayList<Location> getScatterLocations(int radius, int amount, Location centre) {
        ArrayList<Location> locations = new ArrayList<Location>();
        for (int locAmount = 1; locAmount <= amount;) {        
            double scatterx = radius * ranGen.nextDouble() * 2.0D;
            double scatterz = radius * ranGen.nextDouble() * 2.0D;
            
            scatterx -= radius;
            scatterz -= radius;
            
            scatterx += centre.getX();
            scatterz += centre.getZ();
            
            scatterx = Math.round(scatterx) + 0.5D;
            scatterz = Math.round(scatterz) + 0.5D;
            
            Location currentLoc = new Location(centre.getWorld(), scatterx, 0.0D, scatterz);
            currentLoc.setY(centre.getWorld().getHighestBlockYAt(currentLoc));
            
            Material bType = currentLoc.getBlock().getType();
            if(acceptedBlocks.contains(bType)) {
                locations.add(currentLoc);
                locAmount++;
            }            
        }
        return locations;
    }
}
