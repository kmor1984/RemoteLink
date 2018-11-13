package com.sxt;
import java.util.List;

import com.sxt.api.Discover;
import com.sxt.api.ServerLoadBanlance;
import com.sxt.impl.DiscoverImpl;
import com.sxt.impl.ServerLoadBanlanceImpl;

public class DiscoverApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     Discover discover = new DiscoverImpl ();
     List<String> serversDiscover = discover.serversDiscover("com.sxt.core.TestRegist");
     ServerLoadBanlance server = new ServerLoadBanlanceImpl();
     String callServer=server.serverBanlance(serversDiscover);
     System.out.println(callServer);
	}

}
