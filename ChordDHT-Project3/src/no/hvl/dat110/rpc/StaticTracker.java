package no.hvl.dat110.rpc;

/**
 * class that defines the ip addresses of possible active nodes in a ring. In
 * addition, the port for the registry is specified in this class and the number
 * of times a file should be replicated exercise/demo purpose in dat110
 * 
 * @author tdoy
 *
 */

public class StaticTracker {
	// public static String[] ACTIVENODES = {"192.168.1.4"}; // we will implement
	// this as tracker
	public static String[] ACTIVENODES = { "process1" }; // we will implement this as tracker
	// public static String[] ACTIVENODES = {"158.37.71.32"}; // we will implement
	// this as tracker
	public static final int PORT = 9091;
	public static final int N = 4; // number of times a file should be replicated
}
