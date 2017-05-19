package io.dallen.utils.jnbt;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class NBTUtils {

    public static Tag createTag(String name, Object value) {
        Class<?> clazz = value.getClass();
        if ((clazz == Byte.TYPE) || (clazz == Byte.class)) {
            return new ByteTag(name, ((Byte) value).byteValue());
        }
        if ((clazz == Short.TYPE) || (clazz == Short.class)) {
            return new ShortTag(name, ((Short) value).shortValue());
        }
        if ((clazz == Integer.TYPE) || (clazz == Integer.class)) {
            return new IntTag(name, ((Integer) value).intValue());
        }
        if ((clazz == Long.TYPE) || (clazz == Long.class)) {
            return new LongTag(name, ((Long) value).longValue());
        }
        if ((clazz == Float.TYPE) || (clazz == Float.class)) {
            return new FloatTag(name, ((Float) value).floatValue());
        }
        if ((clazz == Double.TYPE) || (clazz == Double.class)) {
            return new DoubleTag(name, ((Double) value).doubleValue());
        }
        if (clazz == byte[].class) {
            return new ByteArrayTag(name, (byte[]) value);
        }
        if (clazz == int[].class) {
            return new IntArrayTag(name, (int[]) value);
        }
        if (clazz == String.class) {
            return new StringTag(name, (String) value);
        }
        if (List.class.isAssignableFrom(clazz)) {
            List<?> list = (List) value;
            if (list.isEmpty()) {
                throw new IllegalArgumentException("cannot set empty list");
            }
            List<Tag> newList = Lists.newArrayList();
            Class<? extends Tag> tagClass = null;
            for (Object v : list) {
                Tag tag = createTag("", v);
                if (tag == null) {
                    throw new IllegalArgumentException("cannot convert list value to tag");
                }
                if (tagClass == null) {
                    tagClass = tag.getClass();
                } else if (tagClass != tag.getClass()) {
                    throw new IllegalArgumentException("list values must be of homogeneous type");
                }
                newList.add(tag);
            }
            return new ListTag(name, tagClass, newList);
        }
        if (Map.class.isAssignableFrom(clazz)) {
            Map<String, Object> map = (Map) value;
            if (map.isEmpty()) {
                throw new IllegalArgumentException("cannot set empty list");
            }
            Map<String, Tag> newMap = Maps.newHashMap();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Tag tag = createTag("", entry.getValue());
                if (tag == null) {
                    throw new IllegalArgumentException("cannot convert map value with key " + (String) entry.getKey() + " to tag");
                }
                newMap.put(entry.getKey(), tag);
            }
            return new CompoundTag(name, newMap);
        }
        return null;
    }

    public static Class<? extends Tag> getTypeClass(int type) {
        switch (type) {
            case 0:
                return EndTag.class;
            case 1:
                return ByteTag.class;
            case 2:
                return ShortTag.class;
            case 3:
                return IntTag.class;
            case 4:
                return LongTag.class;
            case 5:
                return FloatTag.class;
            case 6:
                return DoubleTag.class;
            case 7:
                return ByteArrayTag.class;
            case 8:
                return StringTag.class;
            case 9:
                return ListTag.class;
            case 10:
                return CompoundTag.class;
            case 11:
                return IntArrayTag.class;
        }
        throw new IllegalArgumentException("Invalid tag type : " + type + ".");
    }

    public static int getTypeCode(Class<? extends Tag> clazz) {
        if (clazz.equals(ByteArrayTag.class)) {
            return 7;
        }
        if (clazz.equals(ByteTag.class)) {
            return 1;
        }
        if (clazz.equals(CompoundTag.class)) {
            return 10;
        }
        if (clazz.equals(DoubleTag.class)) {
            return 6;
        }
        if (clazz.equals(EndTag.class)) {
            return 0;
        }
        if (clazz.equals(FloatTag.class)) {
            return 5;
        }
        if (clazz.equals(IntTag.class)) {
            return 3;
        }
        if (clazz.equals(ListTag.class)) {
            return 9;
        }
        if (clazz.equals(LongTag.class)) {
            return 4;
        }
        if (clazz.equals(ShortTag.class)) {
            return 2;
        }
        if (clazz.equals(StringTag.class)) {
            return 8;
        }
        if (clazz.equals(IntArrayTag.class)) {
            return 11;
        }
        throw new IllegalArgumentException("Invalid tag classs (" + clazz.getName() + ").");
    }

    public static String getTypeName(Class<? extends Tag> clazz) {
        if (clazz.equals(ByteArrayTag.class)) {
            return "TAG_Byte_Array";
        }
        if (clazz.equals(ByteTag.class)) {
            return "TAG_Byte";
        }
        if (clazz.equals(CompoundTag.class)) {
            return "TAG_Compound";
        }
        if (clazz.equals(DoubleTag.class)) {
            return "TAG_Double";
        }
        if (clazz.equals(EndTag.class)) {
            return "TAG_End";
        }
        if (clazz.equals(FloatTag.class)) {
            return "TAG_Float";
        }
        if (clazz.equals(IntTag.class)) {
            return "TAG_Int";
        }
        if (clazz.equals(ListTag.class)) {
            return "TAG_List";
        }
        if (clazz.equals(LongTag.class)) {
            return "TAG_Long";
        }
        if (clazz.equals(ShortTag.class)) {
            return "TAG_Short";
        }
        if (clazz.equals(StringTag.class)) {
            return "TAG_String";
        }
        if (clazz.equals(IntArrayTag.class)) {
            return "TAG_Int_Array";
        }
        throw new IllegalArgumentException("Invalid tag classs (" + clazz.getName() + ").");
    }
}
