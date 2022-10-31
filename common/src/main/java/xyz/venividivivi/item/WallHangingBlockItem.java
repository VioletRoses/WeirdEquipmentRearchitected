package xyz.venividivivi.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class WallHangingBlockItem extends BlockItem {
    protected final Block wallBlock;

    public WallHangingBlockItem(Block hangingBlock, Block wallBlock, Settings settings) {
        super(hangingBlock, settings);
        this.wallBlock = wallBlock;
    }


    @Nullable
    protected BlockState getPlacementState(ItemPlacementContext context) {
        BlockState blockState = this.wallBlock.getPlacementState(context);
        Direction side = context.getSide();
        if (side.equals(Direction.DOWN)) {
            return this.getBlock().getDefaultState();
        } else if (side.getAxis().isHorizontal()) {
            return blockState;
        }
        return null;
    }

    public void appendBlocks(Map<Block, Item> map, Item item) {
        super.appendBlocks(map, item);
        map.put(this.wallBlock, item);
    }
}
