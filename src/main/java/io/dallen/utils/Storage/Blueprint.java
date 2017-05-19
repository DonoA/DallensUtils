package io.dallen.utils.Storage;

import io.dallen.utils.BukkitUtils.BlockUtil;
import io.dallen.utils.LogUtil;
import java.awt.Point;
import java.util.Arrays;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

/**
 *
 * @author donoa_000
 */
public class Blueprint implements Cloneable {

    @Getter
    private int len; // Z
    @Getter
    private int wid; // X
    @Getter
    private int high; // Y

    @Getter
    private Point offSet;

    @Getter
    private BlueBlock[][][] blocks; //X Y Z

    public Blueprint(int l, int w, int h, short[] b, byte[] d) {
//        LogUtil.printDebug(Arrays.toString(b));
        this.len = l;
        this.wid = w;
        this.high = h;
        blocks = new BlueBlock[w][h][l];
        int currentOffset = 0;
        for (int y = 0; y < this.high; y++) {
            for (int z = 0; z < this.len; z++) {
                for (int x = 0; x < this.wid; x++) {
                    blocks[x][y][z] = new BlueBlock(b[currentOffset], d[currentOffset]);
                    currentOffset++;
                }
            }
        }
        offSet = new Point(0, 0);
    }

    public Blueprint(int l, int w, int h, BlueBlock[][][] blocks) {
        this.len = l;
        this.wid = w;
        this.high = h;
        this.blocks = blocks.clone();
        offSet = new Point(0, 0);
    }
    
    public Blueprint(BlueBlock[][][] blocks) {
        this(blocks.length, blocks[0].length, blocks[0][0].length, blocks);
    }

    public static enum buildType {

        CLEAR, FRAME;
    }

    public void build(Location start, buildType type) {
        for (int y = 0; y < high; y++) {
            for (int z = 0; z < len; z++) {
                for (int x = 0; x < wid; x++) {
                    Location nLoc = start.clone().add(x, y, z);
                    if (type.equals(buildType.CLEAR)) {
                        nLoc.getBlock().setType(Material.AIR, false);
                    } else if (type.equals(buildType.FRAME)) {
                        Material covMat = blocks[x][y][z].Block;
                        if (covMat.name().contains("STAIRS")) {
                            covMat = Material.QUARTZ_STAIRS;
                        } else if (!covMat.equals(Material.AIR)) {
                            covMat = Material.QUARTZ_BLOCK;
                        }
                        nLoc.getBlock().setType(covMat, false);
                        nLoc.getBlock().setData(blocks[x][y][z].Data, false);
                    }
                }
            }
        }
    }

    public void fakeBuild(Location start, buildType type, Player p) {
        for (int y = 0; y < high; y++) {
            for (int z = 0; z < len; z++) {
                for (int x = 0; x < wid; x++) {
                    Location nLoc = start.clone().add(x, y, z);
                    if (type.equals(buildType.CLEAR)) {
                        nLoc.getBlock().setType(Material.AIR, false);
                    } else if (type.equals(buildType.FRAME)) {
                        Material covMat = blocks[x][y][z].Block;
                        if (covMat.name().contains("STAIRS")) {
                            covMat = Material.QUARTZ_STAIRS;
                        } else if (!covMat.equals(Material.AIR)) {
                            covMat = Material.QUARTZ_BLOCK;
                        }
                        nLoc.getBlock().setType(covMat, false);
                        nLoc.getBlock().setData(blocks[x][y][z].Data, false);
                    }
                }
            }
        }
    }

    @Override
    public Blueprint clone() {
        return new Blueprint(len, wid, high, blocks);
    }

    public void Rotate(int angle) {
        BlueBlock[][][] ret = new BlueBlock[this.wid][this.high][this.len];
        if (angle == 90) {
            final int M = blocks.length;
            final int N = blocks[0][0].length;
            ret = new BlueBlock[N][this.high][M];
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    for (int y = 0; y < this.high; y++) {
                        blocks[r][y][c].Data = (byte) BlockUtil.rotate90(blocks[r][y][c].getBlock(), (int) blocks[r][y][c].getData());
                        ret[c][y][M - 1 - r] = blocks[r][y][c];
                    }
                }
            }
        } else if (angle == -90) {
            final int M = blocks.length;
            final int N = blocks[0][0].length;
            ret = new BlueBlock[N][this.high][M];
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < N; c++) {
                    for (int y = 0; y < this.high; y++) {
                        blocks[r][y][c].Data = (byte) BlockUtil.rotate90Reverse(blocks[r][y][c].getBlock(), (int) blocks[r][y][c].getData());
                        ret[N - 1 - c][y][r] = blocks[r][y][c];
                    }
                }
            }
        }
        this.blocks = ret;
        this.wid = ret.length;
        this.high = ret[0].length;
        this.len = ret[0][0].length;
    }

    static void printMatrix(BlueBlock[][][] mat) {
        LogUtil.printDebug("Matrix = ");
        for (BlueBlock[][] row : mat) {
            LogUtil.printDebug(Arrays.toString(row[0]));
        }
    }

    public short[][][] getRawMats(){
        short[][][] rtn = new short[blocks.length][blocks[0].length][blocks[0][0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                for (int k = 0; k < blocks[i][j].length; k++) {
                    rtn[i][j][k] = (short) blocks[i][j][k].Block.getId();
                }
            }
        }
        return rtn;
    }
    
    public byte[][][] getRawData(){
        byte[][][] rtn = new byte[blocks.length][blocks[0].length][blocks[0][0].length];
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                for (int k = 0; k < blocks[i][j].length; k++) {
                    rtn[i][j][k] = blocks[i][j][k].Data;
                }
            }
        }
        return rtn;
    }
    
    public static class BlueBlock {

        @Getter
        private Material Block;
        
        @Getter
        private byte Data;

        public BlueBlock(short b, byte d) {
            Block = Material.getMaterial(b);
            Data = d;
        }
        
        public BlueBlock(Block b){
            Block = b.getType();
            Data = b.getData();
        }

        @Override
        public String toString() {
            return String.valueOf(Block.getId());
        }
    }
}
