
package com.jfixby.vk.api.data.albums;

import com.jfixby.scarabei.api.json.Json;

public class AlbumsResult {
	public AlbumsResponse response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}

}
