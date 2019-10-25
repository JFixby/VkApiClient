
package com.jfixby.vk.api.data.user;

import java.util.ArrayList;

import com.jfixby.scarabei.api.json.Json;

public class VkProfileResponseResult {
// public VkProfileResponse response = new VkProfileResponse();
	public ArrayList<ProfileInfoResultDataResponse> response = new ArrayList<>();

	@Override
	public String toString () {
		return Json.serializeToString(this).toString();
	}
}
