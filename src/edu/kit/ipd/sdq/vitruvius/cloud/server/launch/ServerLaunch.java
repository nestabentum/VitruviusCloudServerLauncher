package edu.kit.ipd.sdq.vitruvius.cloud.server.launch;

import java.io.IOException;
import org.eclipse.emf.edit.command.SetCommand;

public class ServerLaunch {

	public static void main(String[] args) throws IOException {
		ServerStarter starter = new ServerStarter();
		starter.startServer();
		var test = SetCommand.class;
	}

}
