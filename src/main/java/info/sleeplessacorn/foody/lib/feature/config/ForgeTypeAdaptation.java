package info.sleeplessacorn.foody.lib.feature.config;

import com.google.common.collect.Maps;
import com.google.common.primitives.*;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

// Code copied from MinecraftForge since they hid it all away. I claim no rights on anything in this file.
// Copied classes:
// * net.minecraftforge.common.config.ITypeAdapter (entirely)
// * net.minecraftforge.common.config.TypeAdapters (entirely)
// * net.minecraftforge.common.config.ConfigManager (partly)
public class ForgeTypeAdaptation {

    public static Map<Class<?>, ITypeAdapter> ADAPTERS = Maps.newHashMap();
    public static Map<Class<?>, ITypeAdapter.Map> MAP_ADAPTERS = Maps.newHashMap();

    static {
        register(boolean.class,     TypeAdapters.bool);
        register(boolean[].class,   TypeAdapters.boolA);
        register(Boolean.class,     TypeAdapters.Bool);
        register(Boolean[].class,   TypeAdapters.BoolA);
        register(float.class,       TypeAdapters.flt);
        register(float[].class,     TypeAdapters.fltA);
        register(Float.class,       TypeAdapters.Flt);
        register(Float[].class,     TypeAdapters.FltA);
        register(double.class,      TypeAdapters.dbl);
        register(double[].class,    TypeAdapters.dblA);
        register(Double.class,      TypeAdapters.Dbl);
        register(Double[].class,    TypeAdapters.DblA);
        register(byte.class,        TypeAdapters.byt);
        register(byte[].class,      TypeAdapters.bytA);
        register(Byte.class,        TypeAdapters.Byt);
        register(Byte[].class,      TypeAdapters.BytA);
        register(char.class,        TypeAdapters.chr);
        register(char[].class,      TypeAdapters.chrA);
        register(Character.class,   TypeAdapters.Chr);
        register(Character[].class, TypeAdapters.ChrA);
        register(short.class,       TypeAdapters.shrt);
        register(short[].class,     TypeAdapters.shrtA);
        register(Short.class,       TypeAdapters.Shrt);
        register(Short[].class,     TypeAdapters.ShrtA);
        register(int.class,         TypeAdapters.int_);
        register(int[].class,       TypeAdapters.intA);
        register(Integer.class,     TypeAdapters.Int);
        register(Integer[].class,   TypeAdapters.IntA);
        register(String.class,      TypeAdapters.Str);
        register(String[].class,    TypeAdapters.StrA);
    }

    private static void register(Class<?> cls, ITypeAdapter adpt) {
        ADAPTERS.put(cls, adpt);
        if (adpt instanceof ITypeAdapter.Map)
            MAP_ADAPTERS.put(cls, (ITypeAdapter.Map)adpt);
    }

    interface ITypeAdapter {
        Property getProp(Configuration cfg, String category, Field field, Object instance, String comment);

        Object getValue(Property prop);

        interface Map extends ITypeAdapter {
            Property getProp(Configuration cfg, String category, String name, Object value);
        }
    }

    public static class TypeAdapters {
        static ITypeAdapter bool = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getBoolean(instance, field), comment);
            }
            public Object getValue(Property prop) {
                return prop.getBoolean();
            }
        };
        static ITypeAdapter boolA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (boolean[])getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (boolean[])value, null);
            }
            public Object getValue(Property prop) {
                return prop.getBooleanList();
            }
        };
        static ITypeAdapter Bool = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Boolean)getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Boolean)value, null);
            }
            public Object getValue(Property prop) {
                return Boolean.valueOf(prop.getBoolean());
            }
        };
        static ITypeAdapter BoolA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Booleans.toArray(Arrays.asList((Boolean[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Boolean)value, null);
            }
            public Object getValue(Property prop) {
                return Booleans.asList(prop.getBooleanList()).toArray(new Boolean[prop.getBooleanList().length]);
            }
        };
        static ITypeAdapter flt = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getFloat(instance, field), comment);
            }
            public Object getValue(Property prop) {
                return (float)prop.getDouble();
            }
        };
        static ITypeAdapter fltA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Doubles.toArray(Floats.asList((float[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Doubles.toArray(Floats.asList((float[])value)), null);
            }
            public Object getValue(Property prop) {
                return Floats.toArray(Doubles.asList(prop.getDoubleList()));
            }
        };
        static ITypeAdapter Flt = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Float)getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Float)value, null);
            }
            public Object getValue(Property prop) {
                return Float.valueOf((float)prop.getDouble());
            }
        };
        static ITypeAdapter FltA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Doubles.toArray(Arrays.asList((Float[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Doubles.toArray(Arrays.asList((Float[])value)), null);
            }
            public Object getValue(Property prop) {
                return Floats.asList(Floats.toArray(Doubles.asList(prop.getDoubleList()))).toArray(new Float[prop.getDoubleList().length]);
            }
        };
        static ITypeAdapter dbl = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getDouble(instance, field), comment);
            }
            public Object getValue(Property prop) {
                return prop.getDouble();
            }
        };
        static ITypeAdapter dblA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (double[])getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (double[])value, null);
            }
            public Object getValue(Property prop) {
                return prop.getDoubleList();
            }
        };
        static ITypeAdapter Dbl = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Double)getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Double)value, null);
            }
            public Object getValue(Property prop) {
                return Double.valueOf(prop.getDouble());
            }
        };
        static ITypeAdapter DblA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Doubles.toArray(Arrays.asList((Double[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Doubles.toArray(Arrays.asList((Double[])value)), null);
            }
            public Object getValue(Property prop) {
                return Doubles.asList(prop.getDoubleList()).toArray(new Double[prop.getDoubleList().length]);
            }
        };
        static ITypeAdapter byt = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getByte(instance, field), comment, Byte.MIN_VALUE, Byte.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return (byte)prop.getInt();
            }
        };
        static ITypeAdapter bytA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Ints.toArray(Bytes.asList((byte[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Ints.toArray(Bytes.asList((byte[])value)), null);
            }
            public Object getValue(Property prop) {
                return Bytes.toArray(Ints.asList(prop.getIntList()));
            }
        };
        static ITypeAdapter Byt = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Byte)getObject(instance, field), comment, Byte.MIN_VALUE, Byte.MAX_VALUE);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Byte)value, null, Byte.MIN_VALUE, Byte.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return Byte.valueOf((byte)prop.getInt());
            }
        };
        static ITypeAdapter BytA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Ints.toArray(Arrays.asList((Byte[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Ints.toArray(Arrays.asList((Byte[])value)), null);
            }
            public Object getValue(Property prop) {
                return Bytes.asList(Bytes.toArray(Ints.asList(prop.getIntList()))).toArray(new Byte[prop.getIntList().length]);
            }
        };
        static ITypeAdapter chr = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getChar(instance, field), comment, Character.MIN_VALUE, Character.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return (char)prop.getInt();
            }
        };
        static ITypeAdapter chrA = new MapAdapter() {
            private int[] toPrim(char[] v) {
                if (v == null) return new int[0];
                int[] ret = new int[v.length];
                for (int x = 0; x < v.length; x++)
                    ret[x] = v[x];
                return ret;
            }
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), toPrim((char[])getObject(instance, field)), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, toPrim((char[])value), null);
            }
            public Object getValue(Property prop) {
                int[] v = prop.getIntList();
                char[] ret = new char[v.length];
                for (int x = 0; x < v.length; x++)
                    ret[x] = (char)v[x];
                return ret;
            }
        };
        static ITypeAdapter Chr = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Character)getObject(instance, field), comment, Character.MIN_VALUE, Character.MAX_VALUE);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Character)value, null, Character.MIN_VALUE, Character.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return Character.valueOf((char)prop.getInt());
            }
        };
        static ITypeAdapter ChrA = new MapAdapter() {
            private int[] toPrim(Character[] v) {
                if (v == null) return new int[0];
                int[] ret = new int[v.length];
                for (int x = 0; x < v.length; x++)
                    ret[x] = v[x] == null ? 0 : v[x];
                return ret;
            }
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), toPrim((Character[])getObject(instance, field)), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, toPrim((Character[])value), null);
            }
            public Object getValue(Property prop) {
                int[] v = prop.getIntList();
                Character[] ret = new Character[v.length];
                for (int x = 0; x < v.length; x++)
                    ret[x] = Character.valueOf((char)v[x]);
                return ret;
            }
        };
        static ITypeAdapter shrt = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getShort(instance, field), comment, Short.MIN_VALUE, Short.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return (short)prop.getInt();
            }
        };
        static ITypeAdapter shrtA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Ints.toArray(Shorts.asList((short[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Ints.toArray(Shorts.asList((short[])value)), null);
            }
            public Object getValue(Property prop) {
                return Shorts.toArray(Ints.asList(prop.getIntList()));
            }
        };
        static ITypeAdapter Shrt = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Short)getObject(instance, field), comment, Short.MIN_VALUE, Short.MAX_VALUE);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Short)value, null, Short.MIN_VALUE, Short.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return Short.valueOf((short)prop.getInt());
            }
        };
        static ITypeAdapter ShrtA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Ints.toArray(Arrays.asList((Short[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Ints.toArray(Arrays.asList((Short[])value)), null);
            }
            public Object getValue(Property prop) {
                int[] v = prop.getIntList();
                Short[] ret = new Short[v.length];
                for (int x = 0; x < ret.length; x++)
                    ret[x] = Short.valueOf((short)v[x]);
                return ret;
            }
        };
        static ITypeAdapter int_ = new TypeAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), getInt(instance, field), comment, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return prop.getInt();
            }
        };
        static ITypeAdapter intA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (int[])getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (int[])value, null);
            }
            public Object getValue(Property prop) {
                return prop.getIntList();
            }
        };
        static ITypeAdapter Int = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (Integer)getObject(instance, field), comment, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (Integer)value, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
            public Object getValue(Property prop) {
                return (Integer)prop.getInt();
            }
        };
        static ITypeAdapter IntA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), Ints.toArray(Arrays.asList((Integer[])getObject(instance, field))), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, Ints.toArray(Arrays.asList((Integer[])value)), null);
            }
            public Object getValue(Property prop) {
                return Ints.asList(prop.getIntList()).toArray(new Integer[prop.getIntList().length]);
            }
        };
        static ITypeAdapter.Map Str = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (String)getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (String)value, null);
            }
            public Object getValue(Property prop) {
                return prop.getString();
            }
        };
        static ITypeAdapter StrA = new MapAdapter() {
            public Property getProp(Configuration cfg, String category, Field field, Object instance, String comment) {
                return cfg.get(category, field.getName(), (String[])getObject(instance, field), comment);
            }
            public Property getProp(Configuration cfg, String category, String name, Object value) {
                return cfg.get(category, name, (String[])value, null);
            }
            public Object getValue(Property prop) {
                return prop.getStringList();
            }
        };


        private static abstract class TypeAdapter implements ITypeAdapter
        {
            public static boolean getBoolean(Object instance, Field f)
            {
                try {
                    return f.getBoolean(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
            public static int getInt(Object instance, Field f)
            {
                try {
                    return f.getInt(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
            public static Object getObject(Object instance, Field f)
            {
                try {
                    return f.get(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            public static byte getByte(Object instance, Field f)
            {
                try {
                    return f.getByte(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
            public static char getChar(Object instance, Field f)
            {
                try {
                    return f.getChar(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
            public static double getDouble(Object instance, Field f)
            {
                try {
                    return f.getDouble(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
            public static float getFloat(Object instance, Field f)
            {
                try {
                    return f.getFloat(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
            public static short getShort(Object instance, Field f)
            {
                try {
                    return f.getShort(instance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        }
        private static abstract class MapAdapter extends TypeAdapter implements ITypeAdapter.Map {}
    }
}
