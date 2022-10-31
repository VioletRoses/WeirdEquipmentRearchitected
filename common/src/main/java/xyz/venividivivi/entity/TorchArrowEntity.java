package xyz.venividivivi.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import xyz.venividivivi.registry.WeirdEquipmentEntityTypes;

public class TorchArrowEntity extends PersistentProjectileEntity {

    public final World world;
    public PlayerEntity playerEntity;
    public int fireTime = 3;

    public TorchArrowEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.world = world;
    }

    public TorchArrowEntity(World world, LivingEntity owner) {
        super(WeirdEquipmentEntityTypes.TORCH_ARROW.get(), owner, world);
        this.world = world;
        playerEntity = (PlayerEntity) owner;
    }


    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        Direction side = blockHitResult.getSide();
        BlockPos blockPos = blockHitResult.getBlockPos().offset(side);
        BlockState blockState;

        if (side.getAxis().isHorizontal()) {
            blockState = Blocks.WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, side);
        } else {
            blockState = Blocks.TORCH.getDefaultState();
        }

        if (blockState.canPlaceAt(world, blockPos) && world.getBlockState(blockPos).isAir()) {
            world.playSound(null, blockPos, SoundEvents.BLOCK_WOOD_PLACE, SoundCategory.BLOCKS, 2.25F, 0.9f);
            world.setBlockState(blockPos, blockState);
            world.emitGameEvent(playerEntity, GameEvent.BLOCK_CHANGE, blockPos);
        } else {
            world.playSound(null, blockPos, SoundEvents.ENTITY_ARROW_HIT, SoundCategory.BLOCKS, 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
            world.spawnEntity(new ItemEntity(world, getX(), getY(), getZ(), new ItemStack(Items.TORCH, 1)));
        }
        remove(RemovalReason.DISCARDED);
    }

    @Override
    protected ItemStack asItemStack() {
        return new ItemStack(Items.TORCH);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();
        if (entityHitResult.getEntity() instanceof LivingEntity) {
            // && WeirdEquipmentConfig.TORCH_BOW_CAN_DAMAGE_MOBS
            entity.setOnFire(true);
            entity.setOnFireFor(fireTime);
            entity.damage(DamageSource.arrow(this, this.getOwner()), (float) getDamage());
        } else world.spawnEntity(new ItemEntity(world, getX(), getY(), getZ(), new ItemStack(Items.TORCH, 1)));
        remove(RemovalReason.DISCARDED);
    }
}