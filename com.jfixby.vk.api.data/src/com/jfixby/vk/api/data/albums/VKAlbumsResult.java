
package com.jfixby.vk.api.data.albums;

import com.jfixby.scarabei.api.json.Json;

public class VKAlbumsResult {
	public VKAlbumsResponse response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}

}
