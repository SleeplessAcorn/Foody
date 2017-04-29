package info.sleeplessacorn.foody.feature.mundane.item;

import info.sleeplessacorn.foody.Foody;
import info.sleeplessacorn.foody.feature.mundane.Utensil;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class ItemUtensil extends Item {

    public ItemUtensil() {
        setUnlocalizedName(Foody.MODID + ".utensil");
        setCreativeTab(CreativeTabs.TOOLS);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (Utensil utensil : Utensil.values())
            subItems.add(new ItemStack(item, 1, utensil.ordinal()));
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
