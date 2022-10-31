package xyz.venividivivi.block;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import xyz.venividivivi.registry.WeirdEquipmentBlocks;

public class RopeBlock extends Block {
    public RopeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.offset(Direction.UP)).isSideSolid(world, pos, Direction.DOWN, SideShapeType.CENTER);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.getBlockState(pos.offset(Direction.UP)).isSideSolid(world, pos.offset(Direction.UP), Direction.DOWN, SideShapeType.CENTER)) {
            world.createAndScheduleBlockTick(pos, WeirdEquipmentBlocks.ROPE.get(), 1);
        }
        super.neighborUpdate(state, world, pos, sourceBlock, sourcePos, notify);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(WeirdEquipmentBlocks.ROPE.get(), 1)));
        super.scheduledTick(state, world, pos, random);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.425f, 0f, 0.425f, 0.575f, 1.0f, 0.575f);
    }
}
