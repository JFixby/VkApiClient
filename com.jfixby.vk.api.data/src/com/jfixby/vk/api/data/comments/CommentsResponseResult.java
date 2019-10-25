
package com.jfixby.vk.api.data.comments;

import com.jfixby.scarabei.api.json.Json;

public class CommentsResponseResult {
	public CommentsResponse response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
