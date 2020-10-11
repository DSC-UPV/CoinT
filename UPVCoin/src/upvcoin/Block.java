/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upvcoin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 *
 * @author ericm
 */
public class Block {

    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private String data;
    private int nonce;

    public Block(int index, long timestamp, String previousHash, String data) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.data = data;
        nonce = 0;
        hash = Block.calculateHash(this);
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getData() {
        return data;
    }

    public String str() {
        return index + timestamp + previousHash + data + nonce;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Block #").append(index).append(" {previousHash : ").append(previousHash).append(", ").
                append("timestamp : ").append(new Date(timestamp)).append(", ").append("data : ").append(data).
                append(", ").append("hash : ").append(hash).append("]");
        return builder.toString();

    }

    public static String calculateHash(Block block) {
        if (block != null) {
            MessageDigest digest = null;

            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
            String txt = block.str();
            final byte bytes[] = digest.digest(txt.getBytes());
            final StringBuilder builder = new StringBuilder();

            for (final byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) {
                    builder.append('0');
                }

                builder.append(hex);
            }

            return builder.toString();
        }

        return null;
    }
    
    
    
    public static String zeros(int dif){
        String res = "";
        for (int i = 0; i < dif; i++) {
            res += "0";
        }
        
        return res;
    }
    
    
    public void mineBlock(int difficulty) {
        nonce = 0;

        while (!getHash().substring(0, difficulty).equals(zeros(difficulty))) {
            nonce++;
            hash = Block.calculateHash(this);
        }
    }
}
