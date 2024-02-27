package edu.kit.ipd.sdq.vitruvius.cloud.server.launch;

import java.io.IOException;

public class Launch {

	public static void main(String[] args) throws IOException {
		ServerStarter starter = new ServerStarter();
		starter.startServer();
	}

}
