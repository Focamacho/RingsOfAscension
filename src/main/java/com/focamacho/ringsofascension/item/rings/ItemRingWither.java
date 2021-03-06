package com.focamacho.ringsofascension.item.rings;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRingWither extends ItemRingBase {

    public ItemRingWither(Properties properties, String name, String tooltip) {
        super(properties, name, tooltip);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(!isEnabled()) return;
        if(livingEntity.isPotionActive(Effects.WITHER)) {
            livingEntity.removeActivePotionEffect(Effects.WITHER);
        }
    }

    @Override
    public List<ResourceLocation> getLocations() {
        return super.getLocations(ConfigHolder.ringLocationWither);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHolder.ringWither;
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierWither;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!isEnabled()) return;
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}