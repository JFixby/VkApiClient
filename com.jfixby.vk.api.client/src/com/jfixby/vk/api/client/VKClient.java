
package com.jfixby.vk.api.client;

import java.io.IOException;

import org.picfight.vk.api.album.VKAlbumDataResponseResult;
import org.picfight.vk.api.albums.VKAlbumsResult;
import org.picfight.vk.api.comments.VkCommentsResponseResult;
import org.picfight.vk.api.likes.VkLikesResponseResult;
import org.picfight.vk.api.photo.VKPhotoDescriptionUpdateResponseResult;
import org.picfight.vk.api.photo.VKSinglePhotoInfoResponse;
import org.picfight.vk.api.user.VkProfileResponseResult;
import org.picfight.vk.api.wall.VkWallPostResponseData;

import com.jfixby.scarabei.api.collections.Collections;
import com.jfixby.scarabei.api.collections.Map;
import com.jfixby.scarabei.api.json.Json;
import com.jfixby.scarabei.api.log.L;
import com.jfixby.scarabei.api.net.http.Http;
import com.jfixby.scarabei.api.net.http.HttpConnection;
import com.jfixby.scarabei.api.net.http.HttpConnectionInputStream;
import com.jfixby.scarabei.api.net.http.HttpConnectionSpecs;
import com.jfixby.scarabei.api.net.http.HttpURL;
import com.jfixby.scarabei.api.net.http.METHOD;
import com.jfixby.scarabei.api.sys.Sys;

public class VKClient {

	public static <T> T callMethod (final String methodName, final VKClientParams credentials, final Map<String, String> params,
		final Class<T> expectedResultType) throws IOException {
		final int MAX_TRIES = 7;
		for (int i = 0; i < MAX_TRIES; i++) {
			try {
				final T result = VKClient.callMethodOnce(methodName, credentials, params, expectedResultType);
				return result;
			} catch (final IOException e) {
// e.printStackTrace();
				L.e(e.getMessage());
				Sys.sleep(500);
			}
		}
		return null;
	}

	public static <T> T callMethodOnce (final String methodName, final VKClientParams credentials,
		final Map<String, String> params, final Class<T> expectedResultType) throws IOException {
		final String url_string = "https://api.vk.com/method/" + methodName;
		final HttpURL url = Http.newURL(url_string);

// L.d("call", url);

// final Long vkGroupID = credentials.VKGroupID();
		final String accessToken = credentials.VKAccessToken();
		final String vkAPIVersion = credentials.VkAPIVersion();

		final HttpConnectionSpecs specs = Http.newConnectionSpecs();
		specs.setURL(url);
		specs.setUseAgent(true);
		specs.setConnectTimeout(45 * 1000);
		specs.setMethod(METHOD.GET);
// specs.addRequesrProperty("owner_id", vkGroupID + "");
		specs.addRequesrProperty("lang", "ru");
		specs.addRequesrProperty("access_token", accessToken + "");
		specs.addRequesrProperty("v", vkAPIVersion + "");
		if (params != null) {
			for (final String paramName : params.keys()) {
				specs.addRequesrProperty(paramName, params.get(paramName));
			}
		}
		specs.addRequesrProperty("Accept-Charset", "UTF-8");
		specs.addRequesrProperty("charset", "UTF-8");
		Throwable x = null;
		final HttpConnection connect = Http.newConnection(specs);
		connect.open();
		final HttpConnectionInputStream is = connect.getInputStream();
		is.open();
		String raw_json = null;
		try {
			raw_json = is.readAllToString();
		} catch (final Throwable e) {
			x = e;
		}
		is.close();

		if (x != null) {
			throw new IOException(x);
		}

		if (raw_json.contains("\"error\"")) {
			if (raw_json.contains("\"error_msg\"")) {
				if (raw_json.contains("\"Too many requests per second\"")) {
					throw new IOException("Too many requests per second");
				} else {
				}
			}
		}

// L.d("raw_json");
// Json.printPretty(Json.newJsonString(raw_json));

		final T result = Json.deserializeFromString(expectedResultType, raw_json);

		return result;
	}

	public static VKAlbumsResult listAlbums (final VKClientParams clientParams) throws IOException {
		final VKAlbumsResult result = VKClient.callMethod("photos.getAlbums", clientParams, null, VKAlbumsResult.class);
		return result;
	}

	public static VKAlbumDataResponseResult getAlbum (final VKClientParams clientParams, final Long album_id) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("album_id", album_id.toString());
		final VKAlbumDataResponseResult response = //
			VKClient.callMethod("photos.get", clientParams, params, VKAlbumDataResponseResult.class);
		return response;
	}

	public static VkProfileResponseResult getUserInfo (final VKClientParams clientParams, final String userid) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("user_ids", userid.toString());
		final VkProfileResponseResult response = //
			VKClient.callMethod("users.get", clientParams, params, VkProfileResponseResult.class);
		return response;
	}

	public static VkWallPostResponseData getWallPosts (final VKClientParams clientParams, final WALL_FILTER filter)
		throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("filter", filter.VK_API_PARAM());

		final VkWallPostResponseData response = //
			VKClient.callMethod("wall.get", clientParams, params, VkWallPostResponseData.class);
		return response;
	}

	public static VKSinglePhotoInfoResponse getPhotoInfo (final VKClientParams clientParams, final Long groupID,
		final Long photoID) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("photos", groupID + "_" + photoID);
		final VKSinglePhotoInfoResponse response = //
			VKClient.callMethod("photos.getById", clientParams, params, VKSinglePhotoInfoResponse.class);

		return response;
	}

	public static VkCommentsResponseResult getComments (final VKClientParams clientParams, final Long groupID, final Long photoID)
		throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("type", "photo");
		params.put("photo_id", "" + photoID);
		params.put("need_likes", "1");
		params.put("owner_id", "" + groupID);
		params.put("access_key", "");
		final VkCommentsResponseResult response = //
			VKClient.callMethod("photos.getComments", clientParams, params, VkCommentsResponseResult.class);
		return response;
	}

	public static VkLikesResponseResult getLikes (final VKClientParams clientParams, final Long groupID, final Long photo_id,
		final boolean alsoRequestProfile) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("type", "photo");
		params.put("item_id", "" + photo_id);
		params.put("friends_only", "0");
		params.put("offset", "0");
		params.put("count", "1000000");
		params.put("owner_id", "" + groupID);
		final VkLikesResponseResult response = //
			VKClient.callMethod("likes.getList", clientParams, params, VkLikesResponseResult.class);
		return response;
	}

	public static VKPhotoDescriptionUpdateResponseResult updatePhotoDescription (final VKClientParams clientParams,
		final Long groupID, final Long photoID, final String newDescription) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("caption", newDescription);
		params.put("photo_id", "" + photoID);
		params.put("owner_id", "" + groupID);
		final VKPhotoDescriptionUpdateResponseResult response = //
			VKClient.callMethod("photos.edit", clientParams, params, VKPhotoDescriptionUpdateResponseResult.class);
		return response;
	}

}
