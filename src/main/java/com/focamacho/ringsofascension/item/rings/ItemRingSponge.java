package com.focamacho.ringsofascension.item.rings;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRingSponge extends ItemRingBase {

    public ItemRingSponge(Properties properties, String name, String tooltip) {
        super(properties, name, tooltip);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(!isEnabled()) return;
        if(livingEntity.world.isRemote || livingEntity.isCrouching()) return;

        World world = livingEntity.world;
        BlockPos entityPos = new BlockPos(livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ());
        int range = 3;

        for(BlockPos pos : BlockPos.getAllInBoxMutable(entityPos.getX() - range, entityPos.getY() - range, entityPos.getZ() - range, entityPos.getX() + range, entityPos.getY() + range, entityPos.getZ() + range)) {
            BlockState state = livingEntity.world.getBlockState(pos);
            FluidState fluid = livingEntity.world.getFluidState(pos);
            Material material = state.getMaterial();

            if (fluid.isTagged(FluidTags.WATER)) {
                if (state.getBlock() instanceof IBucketPickupHandler && ((IBucketPickupHandler) state.getBlock()).pickupFluid(world, pos, state) != Fluids.EMPTY) {
                    continue;
                } else if (state.getBlock() instanceof FlowingFluidBlock) {
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                } else if (material == Material.OCEAN_PLANT || material == Material.SEA_GRASS) {
                    TileEntity tileentity = state.getBlock().hasTileEntity(state) ? world.getTileEntity(pos) : null;
                    Block.spawnDrops(state, world, pos, tileentity);
                    world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                }
            }
        }
    }

    @Override
    public List<ResourceLocation> getLocations() {
        return super.getLocations(ConfigHolder.ringLocationSponge);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHolder.ringSponge;
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierSponge;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!isEnabled()) return;
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}
