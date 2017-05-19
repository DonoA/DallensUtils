package io.dallen.utils.jnbt;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

public final class NBTOutputStream
        implements Closeable {

    private final DataOutputStream os;

    public NBTOutputStream(OutputStream os)
            throws IOException {
        this.os = new DataOutputStream(new GZIPOutputStream(os));
    }

    public void close() throws IOException {
        this.os.close();
    }

    private void writeByteArrayTagPayload(ByteArrayTag tag)
            throws IOException {
        byte[] bytes = tag.getValue();
        this.os.writeInt(bytes.length);
        this.os.write(bytes);
    }

    private void writeByteTagPayload(ByteTag tag)
            throws IOException {
        this.os.writeByte(tag.getValue().byteValue());
    }

    private void writeCompoundTagPayload(CompoundTag tag)
            throws IOException {
        for (Tag childTag : tag.getValue().values()) {
            writeTag(childTag);
        }
        this.os.writeByte(0);
    }

    private void writeDoubleTagPayload(DoubleTag tag)
            throws IOException {
        this.os.writeDouble(tag.getValue().doubleValue());
    }

    private void writeEndTagPayload(EndTag tag) {
    }

    private void writeFloatTagPayload(FloatTag tag)
            throws IOException {
        this.os.writeFloat(tag.getValue().floatValue());
    }

    private void writeIntArrayTagPayload(IntArrayTag tag) throws IOException {
        int[] data = tag.getValue();
        this.os.writeInt(data.length);
        for (int element : data) {
            this.os.writeInt(element);
        }
    }

    private void writeIntTagPayload(IntTag tag)
            throws IOException {
        this.os.writeInt(tag.getValue().intValue());
    }

    private void writeListTagPayload(ListTag tag)
            throws IOException {
        Class<? extends Tag> clazz = tag.getType();
        List<Tag> tags = tag.getValue();
        int size = tags.size();

        this.os.writeByte(NBTUtils.getTypeCode(clazz));
        this.os.writeInt(size);
        for (int i = 0; i < size; i++) {
            writeTagPayload((Tag) tags.get(i));
        }
    }

    private void writeLongTagPayload(LongTag tag)
            throws IOException {
        this.os.writeLong(tag.getValue().longValue());
    }

    private void writeShortTagPayload(ShortTag tag)
            throws IOException {
        this.os.writeShort(tag.getValue().shortValue());
    }

    private void writeStringTagPayload(StringTag tag)
            throws IOException {
        byte[] bytes = tag.getValue().getBytes(NBTConstants.CHARSET);
        this.os.writeShort(bytes.length);
        this.os.write(bytes);
    }

    public void writeTag(Tag tag)
            throws IOException {
        int type = NBTUtils.getTypeCode(tag.getClass());
        String name = tag.getName();
        byte[] nameBytes = name.getBytes(NBTConstants.CHARSET);

        this.os.writeByte(type);
        this.os.writeShort(nameBytes.length);
        this.os.write(nameBytes);

        if (type == 0) {
            throw new IOException("Named TAG_End not permitted.");
        }

        writeTagPayload(tag);
    }

    private void writeTagPayload(Tag tag)
            throws IOException {
        int type = NBTUtils.getTypeCode(tag.getClass());
        switch (type) {
            case 0:
                writeEndTagPayload((EndTag) tag);
                break;
            case 1:
                writeByteTagPayload((ByteTag) tag);
                break;
            case 2:
                writeShortTagPayload((ShortTag) tag);
                break;
            case 3:
                writeIntTagPayload((IntTag) tag);
                break;
            case 4:
                writeLongTagPayload((LongTag) tag);
                break;
            case 5:
                writeFloatTagPayload((FloatTag) tag);
                break;
            case 6:
                writeDoubleTagPayload((DoubleTag) tag);
                break;
            case 7:
                writeByteArrayTagPayload((ByteArrayTag) tag);
                break;
            case 8:
                writeStringTagPayload((StringTag) tag);
                break;
            case 9:
                writeListTagPayload((ListTag) tag);
                break;
            case 10:
                writeCompoundTagPayload((CompoundTag) tag);
                break;
            case 11:
                writeIntArrayTagPayload((IntArrayTag) tag);
            default:
                throw new IOException("Invalid tag type: " + type + ".");
        }
    }
}
