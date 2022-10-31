package xyz.venividivivi.block;

import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import xyz.venividivivi.registry.WeirdEquipmentBlocks;

public class WallRopeBlock extends HorizontalFacingBlock {
    public WallRopeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.NORTH));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = this.getDefaultState();
        WorldView worldView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        Direction side = ctx.getSide();
        if (side.getAxis().isHorizontal()) {
            if (blockState.with(FACING, side.getOpposite()).canPlaceAt(worldView, blockPos)) {
                return blockState.with(FACING, side.getOpposite());
            }
        }
        return null;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos);
        return blockState.isSideSolidFullSquare(world, blockPos, direction);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        Direction side = state.get(FACING);
        if (!world.getBlockState(pos.offset(side)).isSideSolid(world, pos.offset(side), side.getOpposite(), SideShapeType.CENTER)) {
            world.createAndScheduleBlockTick(pos, WeirdEquipmentBlocks.WALL_ROPE.get(), 1);
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
        VoxelShape shape1 = VoxelShapes.cuboid(0.425f, 0f, 0.425f, 0.575f, 0.5f, 0.575f);
        VoxelShape shape2 = VoxelShapes.cuboid(0.425f, 0.425f, 0f, 0.575f, 0.575f, 0.575f);
        switch(state.get(FACING)) {
            case NORTH -> shape2 = VoxelShapes.cuboid(0.425f, 0.425f, 0f, 0.575f, 0.575f, 0.575f);
            case EAST -> shape2 = VoxelShapes.cuboid(0.425f, 0.425f, 0.425f, 1f, 0.575f, 0.575f);
            case SOUTH -> shape2 = VoxelShapes.cuboid(0.425f, 0.425f, 0.425f, 0.575f, 0.575f, 1f);
            case WEST -> shape2 = VoxelShapes.cuboid(0f, 0.425f, 0.425f, 0.575f, 0.575f, 0.575f);
        }
        return VoxelShapes.combine(shape1, shape2, BooleanBiFunction.OR);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }
}
