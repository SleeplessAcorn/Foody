package info.sleeplessacorn.foody.util;

import info.sleeplessacorn.foody.Foody;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabFoody extends CreativeTabs {

    public ItemStack display = ItemStack.EMPTY;

    public CreativeTabFoody() {
        super(Foody.MODID);
    }

    @Override
    public ItemStack getTabIconItem() {
        return display;
    }
}
