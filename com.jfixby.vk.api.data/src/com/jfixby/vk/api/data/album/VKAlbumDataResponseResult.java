
package com.jfixby.vk.api.data.album;

import com.jfixby.scarabei.api.json.Json;

public class VKAlbumDataResponseResult {

	public VKAlbumDataResponse response = new VKAlbumDataResponse();

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
