/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xinblock;

/**
 *
 * @author 721292
 */

public class main {
	
	public static void main(String[] args) {
		XinChain blockchain = new XinChain(4);
		
		blockchain.addBlock(blockchain.newBlock("lets have some xin coins"));
		System.out.println("mining in process..");
		
		blockchain.addBlock(blockchain.newBlock("Xin coin is the next big coin"));
		System.out.println("mining in process..");
		
		blockchain.addBlock(blockchain.newBlock("wow this coin is amazing"));
		System.out.println("mining in process..");
		
		System.out.println("Is the block Chain valide?" + blockchain.isBlockChainValid());
	
		System.out.println(blockchain);
	}
}
