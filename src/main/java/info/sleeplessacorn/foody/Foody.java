package info.sleeplessacorn.foody;

import info.sleeplessacorn.foody.lib.feature.Feature;
import info.sleeplessacorn.foody.lib.feature.FeatureHandler;
import info.sleeplessacorn.foody.lib.feature.IFeature;
import info.sleeplessacorn.foody.lib.util.helper.ReflectionHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod(modid = Foody.MODID, name = Foody.NAME, version = "@VERSION@")
public class Foody {

    public static final String MODID = "foody";
    public static final String NAME = "Foody";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    public static FeatureHandler featureHandler;
    public static List<Pair<IFeature, Feature>> features;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        featureHandler = new FeatureHandler(new Configuration(event.getModConfigurationDirectory()));
        features = ReflectionHelper.getAnnotationInstances(event.getAsmData(), Feature.class, IFeature.class);

        featureHandler.preInit(features, event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        featureHandler.init(features, event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        featureHandler.postInit(features, event);
    }
}
