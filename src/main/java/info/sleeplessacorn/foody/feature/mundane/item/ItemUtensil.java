package info.sleeplessacorn.foody.feature.mundane.item;

import info.sleeplessacorn.foody.Foody;
import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.item.ItemStack;
import tehnut.lib.mc.item.ItemEnum;
import tehnut.lib.mc.model.IModeled;

import java.util.List;

public class ItemUtensil extends ItemEnum<Utensil> implements IModeled {

    public ItemUtensil() {
        super(Utensil.class, Foody.MODID + ".utensil");

        setCreativeTab(Foody.TAB_FOODY);
        setMaxStackSize(1);
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return itemStack;
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public void getVariants(List<String> variants) {
        for (Utensil utensil : Utensil.values())
            variants.add("utensil=" + utensil.getName());
    }
}
