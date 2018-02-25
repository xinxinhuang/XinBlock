/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xinblock;
import java.lang.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author 721292
 */
public class XinChain {
    //	/**
//	 * hash
//	 * list of transactions
//	 * @param args
//	 */
//	
//	ArrayList<XinBlock> blockchain = new ArrayList<>();
//	
//	public static void main(String[] args) {
//		String[] genesisTransac = {"this is for you, 20 bitcoins", "the first bitcoin"};
//		XinBlock genesisBlock = new XinBlock(0,genesisTransac);
//		
//		String[] block2Transac = {"today is a nice day, I made 10 bitcoin","I thought its 20 bitcoin"};
//		XinBlock blocksecond = new XinBlock(genesisBlock.getBlockHash(),block2Transac);
//		
//		String[] block3Transac = {"hahah","hohoho", "lalahoho","dadadododo"};
//		XinBlock blockthrid = new XinBlock(blocksecond.getBlockHash(), block3Transac);
//		
//		
//		System.out.println("the first block:"+genesisBlock.getBlockHash());
//		System.out.println("the second block:"+blocksecond.getBlockHash());
//		System.out.println("the thrid block:"+blockthrid.getBlockHash());
//		System.out.println("Time is"+blockthrid.getTimeStamp());
//		System.out.println("Time is"+blocksecond.getTimeStamp());
//		
//	}
	
	private int level;
	private List<XinBlock> blocks;
	
	public XinChain(int level) {
		this.level = level;
		blocks = new ArrayList<>();
		//manually creating the first block, the mother.
		XinBlock Genesisblock = new XinBlock(0, System.currentTimeMillis(), null, "Genesis Block");
		Genesisblock.mineXinBlock(level);
		blocks.add(Genesisblock);
	}
	
	public int getLevel() {
		return level;
	}
	
	public XinBlock latestBlock() {
		return blocks.get(blocks.size()-1);
	}
	
	public XinBlock newBlock(String data) {
		XinBlock latestBlock = latestBlock();
		return new XinBlock(latestBlock.getIndex()+1,System.currentTimeMillis(),latestBlock.getHash(),data);
	}
	
	public void addBlock(XinBlock b) {
		if(b!=null) {
			b.mineXinBlock(level);
			blocks.add(b);
		}
	}
	
	public boolean isFirstBlockValid() {
		
		XinBlock fb= blocks.get(0);
		
		if(fb.getIndex()!=0) {
			return false;
		}
		
		if(fb.getPrevHash()!=null) {
			return false;
		}
		
		if(fb.getHash()== null || !XinBlock.calHash(fb).equals(fb.getHash())){
			return false;
		}
		
		return true;
	}
	
	public boolean isValidnewBlock(XinBlock newBlock, XinBlock prevBlock) {
		if(newBlock !=null && prevBlock !=null) {
			if(prevBlock.getIndex()+1 != newBlock.getIndex()) {
				return false;
			}
			if(newBlock.getPrevHash()==null || !newBlock.calHash(newBlock).equals(newBlock.getHash())) {
				return false;
			}
			if(newBlock.getHash() == null || !XinBlock.calHash(newBlock).equals(newBlock.getHash())) {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
	//all the nodes of this blockchain must be valid 
	public boolean isBlockChainValid() {
		if(!isFirstBlockValid()) {
			return false;
		}
		
		for(int i=1; i<blocks.size();i++) {
			XinBlock currBlock = blocks.get(i);
			XinBlock prevBlock = blocks.get(i-1);
			
			if(!isValidnewBlock(currBlock, prevBlock)) {
				return false;
			}
		}
		
		return true;
		
	}
	
	//toStirng method to print blocks
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (XinBlock block:blocks) {
			builder.append(block).append("\n");
		}
		
		return builder.toString();
	}
	
}
