package info.sleeplessacorn.foody;

import info.sleeplessacorn.foody.proxy.ProxyCommon;
import info.sleeplessacorn.foody.util.CreativeTabFoody;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.lib.forge.feature.Feature;
import tehnut.lib.forge.feature.FeatureHandler;
import tehnut.lib.forge.feature.IFeature;
import tehnut.lib.forge.util.helper.ReflectionHelper;
import tehnut.lib.forge.util.helper.StringHelper;
import tehnut.lib.mc.util.OreDictPrioritized;

import java.io.File;
import java.util.List;

@Mod(modid = Foody.MODID, name = Foody.NAME, version = "@VERSION@")
public class Foody {

    public static final String MODID = "foody";
    public static final String NAME = "Foody";
    public static final Logger LOGGER = LogManager.getLogger(NAME);
    public static final CreativeTabFoody TAB_FOODY = new CreativeTabFoody();

    @Mod.Instance(MODID)
    public static Foody INSTANCE;
    @SidedProxy(clientSide = "info.sleeplessacorn.foody.proxy.ProxyClient", serverSide = "info.sleeplessacorn.foody.proxy.ProxyCommon")
    public static ProxyCommon PROXY;

    public static File configDir;
    public static FeatureHandler featureHandler;
    public static List<Pair<IFeature, Feature>> features;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory(), MODID);
        features = ReflectionHelper.getAnnotationInstances(event.getAsmData(), Feature.class, IFeature.class);
        featureHandler = new FeatureHandler(new Configuration(new File(configDir, MODID + ".cfg")), features);
        OreDictPrioritized.loadPriorities(new File(configDir, "oredict_priorities.json"));

        String featureNames = "";
        for (Pair<IFeature, Feature> feature : features)
            featureNames += "\n  - " + StringHelper.toPretty(feature.getRight().name());

        event.getModMetadata().description += "\nLoaded modules: " + featureNames;

        featureHandler.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        featureHandler.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        featureHandler.postInit(event);
    }
}
