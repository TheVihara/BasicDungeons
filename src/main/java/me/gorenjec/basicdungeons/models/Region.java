package me.gorenjec.basicdungeons.models;

import org.bukkit.Location;

public class Region {
    private String name;
    private Location start;
    private Location end;


    public Region(String name, Location loc1, Location loc2){
        this.name = name;
        setLocations(loc1, loc2);
    }

    protected void setLocations(Location start, Location end) {
        if (!start.getWorld().equals(end.getWorld())) {
            throw new IllegalArgumentException("Locations must be in the same world");
        }
        double minX = Math.min(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double minZ = Math.min(start.getZ(), end.getZ());

        double maxX = Math.max(start.getX(), end.getX());
        double maxY = Math.max(start.getY(), end.getY());
        double maxZ = Math.max(start.getZ(), end.getZ());

        this.start = new Location(start.getWorld(), minX, minY, minZ);
        this.end = new Location(end.getWorld(), maxX, maxY, maxZ);
    }


    public boolean contains(Location loc) {
        return loc.getWorld().getName().equals(start.getWorld().getName()) &&
                loc.getX() >= start.getX() && loc.getY() >= start.getY() && loc.getZ() >= start.getZ() &&
                loc.getX() < end.getX() && loc.getY() < end.getY() && loc.getZ() < end.getZ();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getEnd() {
        return end;
    }

    public Location getStart() {
        return start;
    }
}
