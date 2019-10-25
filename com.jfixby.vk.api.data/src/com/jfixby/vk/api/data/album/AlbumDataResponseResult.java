
package com.jfixby.vk.api.data.album;

import com.jfixby.scarabei.api.json.Json;

public class AlbumDataResponseResult {

	public AlbumDataResponse response = new AlbumDataResponse();

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
