
package com.jfixby.vk.api.data.likes;

import com.jfixby.scarabei.api.json.Json;

public class LikesResponseResult {
	public LikesResponse response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
