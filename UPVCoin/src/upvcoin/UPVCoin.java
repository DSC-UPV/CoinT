/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upvcoin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author ericm
 */
public class UPVCoin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Blockchain blockchain = new Blockchain(4);
        blockchain.addBlock(blockchain.newBlock("Hello"));
        blockchain.addBlock(blockchain.newBlock("World"));
        blockchain.addBlock(blockchain.newBlock(":)"));
        
        System.out.println("Blockchain is valid? " + blockchain.isBlockchainValid());
        System.out.println(blockchain);
        
        
        //invalid bloxk test
        /*blockchain.addBlock(new Block(15, System.currentTimeMillis(), "hgfdjdfjydtgdsg", "Block invalid"));
        System.out.println("Blockchain is valid? " + blockchain.isBlockchainValid());
        System.out.println(blockchain);*/
        
        
    }
      
}


