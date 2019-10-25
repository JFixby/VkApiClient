
package com.jfixby.vk.api.data.album;

import java.util.ArrayList;

import com.jfixby.scarabei.api.json.Json;

public class VKAlbumDataResponse {

	public Long count;
	public ArrayList<VKAlbumPhotoData> items = new ArrayList<>();

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
