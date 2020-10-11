/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upvcoin;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author ericm
 */
public class Blockchain {
    
    private int difficulty;
    private List<Block> blocks;
    
    public Blockchain(int difficulty) {
        this.difficulty = difficulty;
        blocks = new ArrayList<>();
        //create first block
        Block b = new Block(0, System.currentTimeMillis(), null, "First Block");
        b.mineBlock(difficulty);
        blocks.add(b);
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public Block latestBlock() {
        return blocks.get(blocks.size() - 1);
    }
    
    public Block newBlock(String data) {
        Block latestBlock = latestBlock();
        return new Block(latestBlock.getIndex() + 1, System.currentTimeMillis(),
            latestBlock.getHash(), data);
    }
    
    
    public void addBlock(Block b) {
        if (b != null) {
            b.mineBlock(difficulty);
            blocks.add(b);
        }
    }
    
    
    public boolean isFirstBlockValid() {
        Block firstBlock = blocks.get(0);
        
        if (firstBlock.getIndex() != 0) {
            return false;
        }
        
        if (firstBlock.getPreviousHash() != null) {
            return false;
        }
        
        if (firstBlock.getHash() == null || 
            !Block.calculateHash(firstBlock).equals(firstBlock.getHash())) {
            return false;
        }
        
        return true;
        
        
    }
    
    public boolean isValidNewBlock(Block newBlock, Block previousBlock) {
        if (newBlock != null && previousBlock != null) {
            if(previousBlock.getIndex() + 1 != newBlock.getIndex()) {
                return false;
            }
            
            if (newBlock.getPreviousHash() == null || 
                    !Block.calculateHash(newBlock).equals(newBlock.getHash())) {
                return false;
            }
            
            return true;
        }
        
        return false;
    }
    
    
    public boolean isBlockchainValid() {
        if (!isFirstBlockValid()) {
            return false;
        }
        
        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i - 1);
            
            if (!isValidNewBlock(currentBlock, previousBlock)) {
                return false;
            }
        }
        
        return true;
    }
    
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        for (Block block : blocks) {
            builder.append(block).append("\n");
        }
        return builder.toString();
    }
                     
}
