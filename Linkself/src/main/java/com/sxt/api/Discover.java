package com.sxt.api;

import java.util.List;

public interface Discover {
//根据请求寻找服务
	List<String> serversDiscover (String serverName);
}
