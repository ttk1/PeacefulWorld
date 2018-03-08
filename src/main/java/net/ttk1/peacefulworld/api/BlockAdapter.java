package net.ttk1.peacefulworld.api;

import org.bukkit.Location;
import org.bukkit.Material;

/**
 * @author ttk1 and mmtsk
 */

public interface BlockAdapter {
    Location getLocation();
    Material getType();
    int getTypeId();
    byte getData();
}
