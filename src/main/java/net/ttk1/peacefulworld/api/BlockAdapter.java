package net.ttk1.peacefulworld.api;

import net.ttk1.peacefulworld.history.BlockAdapterImpl;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author ttk1 and mmtsk
 */
public interface BlockAdapter {
    public static BlockAdapter of(Block block) {
        return new BlockAdapterImpl(block);
    }
    public static BlockAdapter of(Location loc, Material type, int typeId, byte data) {
        return new BlockAdapterImpl(loc, type, typeId, data);
    }
    Location getLocation();
    Material getType();
    int getTypeId();
    byte getData();
}
