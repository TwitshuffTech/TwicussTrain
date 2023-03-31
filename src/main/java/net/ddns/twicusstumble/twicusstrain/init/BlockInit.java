package net.ddns.twicusstumble.twicusstrain.init;

import net.ddns.twicusstumble.twicusstrain.block.BlockYellowLine;
import net.ddns.twicusstumble.twicusstrain.event.IBlockRegisterEvent;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {
    public static final List<IBlockRegisterEvent> BLOCKS = new ArrayList<>();

    public static final Block BLOCK_YELLOW_LINE = new BlockYellowLine("yellow_line", 2.0f, 10.0f, SoundType.STONE);
}
