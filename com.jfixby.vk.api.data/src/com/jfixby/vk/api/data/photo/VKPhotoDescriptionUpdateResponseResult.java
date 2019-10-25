
package com.jfixby.vk.api.data.photo;

import com.jfixby.scarabei.api.json.Json;

public class VKPhotoDescriptionUpdateResponseResult {

	public int response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
