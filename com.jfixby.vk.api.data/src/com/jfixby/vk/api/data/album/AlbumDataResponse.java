
package com.jfixby.vk.api.data.album;

import java.util.ArrayList;

import com.jfixby.scarabei.api.json.Json;

public class AlbumDataResponse {

	public Long count;
	public ArrayList<AlbumPhotoData> items = new ArrayList<>();

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
