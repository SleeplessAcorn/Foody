package info.sleeplessacorn.foody.feature.mundane;

import com.google.common.collect.Lists;
import info.sleeplessacorn.foody.feature.mundane.data.Food;
import info.sleeplessacorn.foody.lib.feature.Feature;
import info.sleeplessacorn.foody.lib.feature.IFeature;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

@Feature(name = "mundane", description = "Mundane foods")
public class FeatureMundane implements IFeature {

    public static final List<Food> FOODS = Lists.newArrayList();

    @Override
    public void preInit(FMLPreInitializationEvent event) {

    }

    @Override
    public void init(FMLInitializationEvent event) {

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Override
    public void handleConfig(Configuration config, String category) {

    }
}
