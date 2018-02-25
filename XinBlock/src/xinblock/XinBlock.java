/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xinblock;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class XinBlock {
	
	private int index;
	private int nonce;

	private String prevHash;
	private String data;
	private long timeStamp;

	private int blockHash;
	private String hash;
	
	public static int level = 500;

	public XinBlock(int index, long timeStamp, String prevHash, String data) {
		this.index = index;
		this.prevHash = prevHash;
		this.data = data;
		this.timeStamp = new Date().getTime();
		
		//Object[] contents = {Arrays.hashCode(data), prevHash, timeStamp};
		//this.blockHash = Arrays.hashCode(contents);
		
		nonce = 0;
		hash = XinBlock.calHash(this);
		
	}

	public static String calHash(XinBlock xinBlock) {
		if(xinBlock!=null) {
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				return null;
			}
			String txt = xinBlock.str();
			final byte bytes[] = digest.digest(txt.getBytes());
			final StringBuilder builder = new StringBuilder();
			
			for(final byte b:bytes) {
				String hex = Integer.toHexString(0xff & b);
				
				if(hex.length()==1) {
					builder.append("0");
				}
				
				builder.append(hex);
			}
			return builder.toString();
		}
		return null;
	}

	private String str() {
		return index + timeStamp + prevHash+ data + nonce;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Block # ").append(index).append(" nonce: ").append(nonce).append(" prevhash:").append(prevHash).append(",").
		append(" timeStamp:").append(new Date(timeStamp)).append(",").append(" data:").append(data).append(",").
		append(" hash:").append(hash).append("]");
		return builder.toString();
	}

	public int getIndex() {
		return index;
	}
	public String getPrevHash() {
		return prevHash;
	}
	
	public String getHash() {
		return hash;
	}

	public String getDatac() {
		return data;
	}

	public int getBlockHash() {
		return blockHash;
	}
	
	public long getTimeStamp() {
		return timeStamp= new Date().getTime();
	}
	
	//proof of work
	public void mineXinBlock(int level) {
		nonce = 0;
		//play around this level +/- to increase or decrease difficulty 
		//incrase diffculty means longer time to mine the block
		level = 5;
		while(!getHash().substring(0, level).equals(Util.zeros(level))) {
			nonce++;
			hash = XinBlock.calHash(this);
		}
		System.out.println("block mined");
	}
	
	
	
}
