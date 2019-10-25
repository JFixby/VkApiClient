
package com.jfixby.vk.api.data.photo;

import java.util.ArrayList;

import com.jfixby.scarabei.api.json.Json;
import com.jfixby.vk.api.data.album.AlbumPhotoData;

public class SinglePhotoInfoResponse {
	public ArrayList<AlbumPhotoData> response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
