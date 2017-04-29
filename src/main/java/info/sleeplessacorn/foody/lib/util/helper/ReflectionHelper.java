package info.sleeplessacorn.foody.lib.util.helper;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import info.sleeplessacorn.foody.lib.feature.Feature;
import info.sleeplessacorn.foody.lib.feature.IFeature;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReflectionHelper {

    public static final Map<Class<?>, IAnnotationPreloader> PRELOADERS = Maps.newHashMap();
    public static final Map<Class<?>, IAnnotationLoader<?>> LOADERS = Maps.newHashMap();

    static {
        PRELOADERS.put(IFeature.class, Feature.PRELOADER);

        LOADERS.put(IFeature.class, Feature.LOADER);
    }

    /**
     * Populates a list with <b>all</b> fields (including inherited fields) of the given class that fit the filter.
     *
     * @param fields - List of fields to populate
     * @param type - The class to get the fields of
     * @param fieldFilter - Definition of fields that should be collected
     */
    public static void getAllFields(Collection<Field> fields, Class<?> type, Predicate<Field> fieldFilter) {
        for (Field field : type.getDeclaredFields())
            if (fieldFilter.apply(field))
                fields.add(field);

        if (type.getSuperclass() != null)
            getAllFields(fields, type.getSuperclass(), fieldFilter);
    }

    /**
     * Populates a list with <b>all</b> fields (including inherited fields) of the given class.
     *
     * @param fields - List of fields to populate
     * @param type - The class to get the fields of
     */
    public static void getAllFields(Collection<Field> fields, Class<?> type) {
        getAllFields(fields, type, Predicates.<Field>alwaysTrue());
    }

    /**
     * Sets all passed fields as accessible.
     *
     * @param fields - A list of fields to allow access to
     */
    public static void permitFields(Collection<Field> fields) {
        try {
            for (Field field : fields)
                if (!field.isAccessible())
                    field.setAccessible(true);
        } catch (SecurityException e) {
            // No-op
        }
    }

    /**
     * Gathers instances of classes that are annotated with the given annotation.
     *
     * If your annotation contains data that could/should potentially stop the class from loading, register a new
     * {@link IAnnotationPreloader} in {@link #PRELOADERS}.
     *
     * If your type class requires special handling for instance creation, register a new {@link IAnnotationLoader} in
     * {@link #LOADERS}.
     *
     * @param dataTable - The data table that Forge collects
     * @param annotationClass - The annotation class to poll for
     * @param typeClass - The type of class this is
     * @return a list of type instances paired with annotation instances.
     */
    @SuppressWarnings("unchecked")
    @Nonnull
    public static <TYPE, ANNOTATION extends Annotation> List<Pair<TYPE, ANNOTATION>> getAnnotationInstances(ASMDataTable dataTable, Class<ANNOTATION> annotationClass, Class<TYPE> typeClass) {
        List<Pair<TYPE, ANNOTATION>> discoveredAnnotations = Lists.newArrayList();
        Set<ASMDataTable.ASMData> discoveredPlugins = dataTable.getAll(annotationClass.getCanonicalName());

        for (ASMDataTable.ASMData data : discoveredPlugins) {
            if (PRELOADERS.containsKey(typeClass)) {
                IAnnotationPreloader preloader = PRELOADERS.get(typeClass);
                if (!preloader.shouldLoad(data.getAnnotationInfo()))
                    continue;
            }

            try {
                Class<?> asmClass = Class.forName(data.getClassName());
                Class<? extends TYPE> pluginClass = asmClass.asSubclass(typeClass);

                TYPE instance;
                if (LOADERS.containsKey(typeClass))
                    instance = (TYPE) LOADERS.get(typeClass).createInstance((Class<?>) pluginClass, data.getAnnotationInfo());
                else
                    instance = pluginClass.newInstance();

                discoveredAnnotations.add(Pair.of(instance, pluginClass.getAnnotation(annotationClass)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return discoveredAnnotations;
    }

    public interface IAnnotationPreloader {

        /**
         * Called just before {@link #getAnnotationInstances(ASMDataTable, Class, Class)} attempts to load the discovered class.
         *
         * @param annotationData - A map of field name -> field value from the annotation
         * @return whether this class should be loaded.
         */
        boolean shouldLoad(Map<String, Object> annotationData);
    }

    public interface IAnnotationLoader<T> {

        /**
         * Factory method to create a new instance of a class.
         *
         * @param type - The class type being loaded
         * @param annotationData - A map of field name -> field value from the annotation
         * @return a new instance of the class type.
         */
        @Nonnull
        T createInstance(Class<?> type, Map<String, Object> annotationData) throws Exception;
    }
}
