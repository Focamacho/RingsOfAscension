package com.focamacho.ringsofascension.item.rings.effects;

import com.focamacho.ringsofascension.config.ConfigHolder;
import com.focamacho.ringsofascension.item.ItemRingBase;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemRingFireResistance extends ItemRingBase {

    public ItemRingFireResistance(Properties properties, String name, String tooltip) {
        super(properties, name, tooltip);
    }

    @Override
    public void tickCurio(String identifier, int index, LivingEntity livingEntity) {
        if(!isEnabled()) return;
        if(!livingEntity.isPotionActive(Effects.FIRE_RESISTANCE)) {
            EffectInstance effectInstance = new EffectInstance(Effects.FIRE_RESISTANCE, Integer.MAX_VALUE, ConfigHolder.ringAmplifierFireResistance, false, false);
            if(livingEntity.world.isRemote) effectInstance.setPotionDurationMax(true);
            livingEntity.addPotionEffect(effectInstance);
        }
    }

    @Override
    public void onUnequippedCurio(String identifier, LivingEntity livingEntity) {
        if(!isEnabled()) return;
        livingEntity.removePotionEffect(Effects.FIRE_RESISTANCE);
    }

    @Override
    public List<ResourceLocation> getLocations() {
        return super.getLocations(ConfigHolder.ringLocationFireResistance);
    }

    @Override
    public boolean isEnabled() {
        return ConfigHolder.ringFireResistance;
    }

    @Override
    public int getTier() {
        return ConfigHolder.ringTierFireResistance;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(!isEnabled()) return;
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

}