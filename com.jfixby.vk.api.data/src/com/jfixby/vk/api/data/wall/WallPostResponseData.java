
package com.jfixby.vk.api.data.wall;

import com.jfixby.scarabei.api.json.Json;

public class WallPostResponseData {
	public WallPostResponse response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
