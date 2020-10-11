/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upvcoin;
import java.util.Scanner;
/**
 *
 * @author ericm
 */
public class UPVCoin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int num;
        String str;
        Scanner in = new Scanner(System.in);
        Scanner in2 = new Scanner(System.in);
        
        System.out.println("How many Blocks do you want to add? ");
        num = in.nextInt();
        System.out.println("Blocks to add: " + num);
        
        Blockchain blockchain = new Blockchain(num);
        
        for (int i = 1; i < num + 1; i++) {
            System.out.println("Enter data for Block #" + i);
            str = in2.nextLine();
            //str = String.valueOf(i);
            System.out.println("Data for Block #" + i + " : " + str);
            blockchain.addBlock(blockchain.newBlock(str));
        }
        
        /*Blockchain blockchain = new Blockchain(4);
        blockchain.addBlock(blockchain.newBlock("Hello"));
        blockchain.addBlock(blockchain.newBlock("World"));
        blockchain.addBlock(blockchain.newBlock(":)"));*/
        
        System.out.println("Blockchain is valid? " + blockchain.isBlockchainValid());
        System.out.println(blockchain);
        
        
        //invalid bloxk test
        /*blockchain.addBlock(new Block(15, System.currentTimeMillis(), "hgfdjdfjydtgdsg", "Block invalid"));
        System.out.println("Blockchain is valid? " + blockchain.isBlockchainValid());
        System.out.println(blockchain);*/
        
        
    }
      
}


