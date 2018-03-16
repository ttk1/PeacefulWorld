package net.ttk1.peacefulworld.history;

import net.ttk1.peacefulworld.api.BlockAdapter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * @author ttk1 and mmtsk
 */
public class BlockAdapterImpl implements BlockAdapter {
    private final Location loc;
    private final Material type;
    private final int typeId;
    private final byte data;

    public BlockAdapterImpl(Block block) {
        this(block.getLocation(), block.getType(), block.getTypeId(), block.getData());
    }

    public BlockAdapterImpl(Location loc, Material type, int typeId, byte data){
        this.loc = loc;
        this.type = type;
        this.typeId = typeId;
        this.data = data;
    }

    @Override
    public Location getLocation() {
        return loc;
    }

    @Override
    public Material getType() {
        return type;
    }

    @Override
    public int getTypeId() {
        return typeId;
    }

    @Override
    public byte getData() {
        return data;
    }
}
