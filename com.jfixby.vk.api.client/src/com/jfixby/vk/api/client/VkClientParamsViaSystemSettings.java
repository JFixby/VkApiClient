
package com.jfixby.vk.api.client;

import com.jfixby.scarabei.api.debug.Debug;
import com.jfixby.scarabei.api.sys.settings.SystemSettings;

public class VkClientParamsViaSystemSettings implements VKClientParams {
// @Override
// public Long VKGroupID () {
// final String str = SystemSettings.getStringParameter(VKCredentials.VKGroupID(), null);
// Debug.checkNull("" + VKCredentials.VKGroupID(), str);
//
// final Long accessKeyID = Long.parseLong(str);
// return accessKeyID;
// }

	@Override
	public String VKAccessToken () {
		final String str = SystemSettings.getStringParameter(VKCredentials.VKAccessToken(), null);
		Debug.checkNull("" + VKCredentials.VKAccessToken(), str);
		return str;
	}

	@Override
	public String VkAPIVersion () {
// return "5.101";
		final String str = SystemSettings.getStringParameter(VKCredentials.VkAPIVersion(), null);
		Debug.checkNull("" + VKCredentials.VkAPIVersion(), str);
		return str;
	}
};
