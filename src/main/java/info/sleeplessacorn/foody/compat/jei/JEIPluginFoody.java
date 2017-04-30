package info.sleeplessacorn.foody.compat.jei;

import info.sleeplessacorn.foody.feature.FeatureCommon;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.Item;

@JEIPlugin
public class JEIPluginFoody extends BlankModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        for (Item item : FeatureCommon.NBT_SUBTYPES)
            subtypeRegistry.useNbtForSubtypes(item);
    }
}
