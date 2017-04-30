package info.sleeplessacorn.foody.feature.mundane;

import com.google.common.collect.Maps;
import info.sleeplessacorn.foody.feature.mundane.data.Chef;
import info.sleeplessacorn.foody.feature.mundane.data.Food;
import info.sleeplessacorn.foody.feature.mundane.data.FoodType;
import info.sleeplessacorn.foody.feature.mundane.item.ItemFoodyFood;
import info.sleeplessacorn.foody.feature.mundane.item.ItemUtensil;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tehnut.lib.forge.feature.Feature;
import tehnut.lib.forge.feature.IFeature;

import java.util.Map;

@Feature(name = "mundane", description = "Mundane foods")
public class FeatureMundane implements IFeature {

    public static final Map<String, Food> FOODS = Maps.newHashMap();

    public static final Item FOOD = new ItemFoodyFood();
    public static final Item UTENSIL = new ItemUtensil();

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        GameRegistry.register(FOOD.setRegistryName("food"));
        GameRegistry.register(UTENSIL.setRegistryName("utensil"));

        new Chef("toast").asType(FoodType.SOLID).withIngredients(Items.BREAD).cook();
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
