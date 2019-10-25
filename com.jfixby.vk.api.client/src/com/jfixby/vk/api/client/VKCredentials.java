
package com.jfixby.vk.api.client;

import com.jfixby.scarabei.api.names.ID;
import com.jfixby.scarabei.api.names.Names;

public class VKCredentials {
	public static ID VKGroupID () {
		return Names.newID("com_vk_group_id");
	}

	public static ID VKAccessToken () {
		return Names.newID("com_vk_api_ACCESS_TOKEN");
	}

	public static ID VkAPIVersion () {
		return Names.newID("com_vk_api_version");
	}

}
