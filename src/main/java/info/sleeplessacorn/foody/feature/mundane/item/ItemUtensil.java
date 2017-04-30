package info.sleeplessacorn.foody.feature.mundane.item;

import info.sleeplessacorn.foody.Foody;
import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tehnut.lib.mc.item.ItemEnum;

public class ItemUtensil extends ItemEnum<Utensil> {

    public ItemUtensil() {
        super(Utensil.class, Foody.MODID + ".utensil");

        setCreativeTab(CreativeTabs.TOOLS);
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
}
