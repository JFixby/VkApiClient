
package com.jfixby.vk.api.data.photo;

import java.util.ArrayList;

import com.jfixby.scarabei.api.json.Json;
import com.jfixby.vk.api.data.album.VKAlbumPhotoData;

public class VKSinglePhotoInfoResponse {
	public ArrayList<VKAlbumPhotoData> response;

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
