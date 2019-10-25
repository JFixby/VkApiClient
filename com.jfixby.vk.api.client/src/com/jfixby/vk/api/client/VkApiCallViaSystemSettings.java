
package com.jfixby.vk.api.client;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class VkApiCallViaSystemSettings implements VkApiCallParams {

	@Override
	public String VKAccessToken () {
		final String str = SystemSettings.getStringParameter(VKCredentials.VKAccessToken(), null);
		Debug.checkNull("" + VKCredentials.VKAccessToken(), str);
		return str;
// https://oauth.vk.com/authorize?client_id=5110427&scope=photos,audio,video,docs,notes,pages,status,offers,questions,wall,groups,email,notifications,stats,ads,offline,docs,pages,stats,notifications&response_type=token
	}

	@Override
	public String VkAPIVersion () {
// return "5.101";
		final String str = SystemSettings.getStringParameter(VKCredentials.VkAPIVersion(), null);
		Debug.checkNull("" + VKCredentials.VkAPIVersion(), str);
		return str;
	}
};
