package xyz.venividivivi.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class NetheriteTorchPickaxeItem extends PickaxeItem {
    public NetheriteTorchPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Direction side = context.getSide();
        BlockPos blockPos = context.getBlockPos().offset(side);
        BlockState blockState;
        if (side.getAxis().isHorizontal()) {
            blockState = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, side);
        } else {
            blockState = Blocks.TORCH.getDefaultState();
        }
        if (blockState.canPlaceAt(world, blockPos) && world.getBlockState(blockPos).isAir()) {
            PlayerEntity playerEntity = context.getPlayer();
            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 1.0F, 0.9f);
            world.setBlockState(blockPos, blockState);
            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
            if (playerEntity instanceof ServerPlayerEntity) {
                context.getStack().damage(5, playerEntity, (p) -> p.sendToolBreakStatus(context.getHand()));
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
