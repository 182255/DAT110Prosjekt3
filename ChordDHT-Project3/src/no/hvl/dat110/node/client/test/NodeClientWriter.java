package no.hvl.dat110.node.client.test;

import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import no.hvl.dat110.file.FileManager;
import no.hvl.dat110.rpc.StaticTracker;
import no.hvl.dat110.rpc.interfaces.ChordNodeInterface;
import no.hvl.dat110.util.Hash;
import no.hvl.dat110.util.Util;

public class NodeClientWriter extends Thread {

	private boolean succeed = false;
	private String content;
	private String filename;
	
	public NodeClientWriter(String content, String filename) {
		this.content = content;
		this.filename = filename;
	}
	
	public void run() {
		sendRequest();
	}
	
	private void sendRequest() {
		
		// Lookup(key) - Use this class as a client that is requesting for a new file and needs the identifier and IP of the node where the file is located
		// assume you have a list of nodes in the tracker class and select one randomly. We can use the Tracker class for this purpose
		String active = StaticTracker.ACTIVENODES[0];
		
		// connect to an active chord node - can use the process defined in StaticTracker 
		BigInteger ip = Hash.hashOf(active);
		
		Registry r = Util.locateRegistry(ip.toString());
		// Compute the hash of the node's IP address
		try {
			// use the hash to retrieve the ChordNodeInterface remote object from the registry
			ChordNodeInterface node = (ChordNodeInterface) r.lookup(ip.toString());
			// do: FileManager fm = new FileManager(ChordNodeInterface, StaticTracker.N);
			FileManager fm = new FileManager(node, StaticTracker.N);
			// do: boolean succeed = fm.requestWriteToFileFromAnyActiveNode(filename, content);
			succeed = fm.requestWriteToFileFromAnyActiveNode(filename, content);
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
					
		
	}
	
	public boolean isSucceed() {
		return succeed;
	}

	public void setSucceed(boolean succeed) {
		this.succeed = succeed;
	}

}
