package net.ddns.twicusstumble.twicusstrain.entity;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

public class EntityTrain extends EntityMinecart {
    public EntityTrain(World worldIn) {
        super(worldIn);
    }

    public EntityTrain(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public Type getType() {
        return Type.RIDEABLE;
    }
}
