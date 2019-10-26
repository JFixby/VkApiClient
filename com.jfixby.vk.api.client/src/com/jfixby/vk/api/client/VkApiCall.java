
package com.jfixby.vk.api.client;

import java.io.IOException;

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
import com.jfixby.vk.api.data.album.AlbumDataResponseResult;
import com.jfixby.vk.api.data.albums.AlbumsResult;
import com.jfixby.vk.api.data.comments.CommentsResponseResult;
import com.jfixby.vk.api.data.likes.LikesResponseResult;
import com.jfixby.vk.api.data.photo.PhotoDescriptionUpdateResponseResult;
import com.jfixby.vk.api.data.photo.SinglePhotoInfoResponse;
import com.jfixby.vk.api.data.user.ProfileResponseResult;
import com.jfixby.vk.api.data.wall.WallPostResponseData;

public class VkApiCall {

	public static <T> T callMethod (final String methodName, final VkApiCallParams credentials, final Map<String, String> params,
		final Class<T> expectedResultType) throws IOException {
		final int MAX_TRIES = 7;
		IOException x = null;
		for (int i = 0; i < MAX_TRIES; i++) {
			try {
				final T result = VkApiCall.callMethodOnce(methodName, credentials, params, expectedResultType);
				return result;
			} catch (final IOException e) {
// e.printStackTrace();
				L.e(methodName, e.getMessage());
				x = e;
				Sys.sleep(500);
			}
		}
		throw x;
	}

	public static <T> T callMethodOnce (final String methodName, final VkApiCallParams credentials,
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
// Err.reportError("");
		final T result = Json.deserializeFromString(expectedResultType, raw_json);

		return result;
	}

	public static AlbumsResult listAlbums (final VkApiCallParams clientParams, final Long ownerID) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("owner_id", ownerID.toString());
		final AlbumsResult result = VkApiCall.callMethod("photos.getAlbums", clientParams, params, AlbumsResult.class);
		return result;
	}

	public static AlbumDataResponseResult getAlbum (final VkApiCallParams clientParams, final Long album_id) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("album_id", album_id.toString());
		final AlbumDataResponseResult response = //
			VkApiCall.callMethod("photos.get", clientParams, params, AlbumDataResponseResult.class);
		return response;
	}

	public static ProfileResponseResult getUserInfo (final VkApiCallParams clientParams, final String userid) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("user_ids", userid.toString());
		final ProfileResponseResult response = //
			VkApiCall.callMethod("users.get", clientParams, params, ProfileResponseResult.class);
		return response;
	}

	public static WallPostResponseData getWallPosts (final VkApiCallParams clientParams, final WALL_FILTER filter)
		throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("filter", filter.VK_API_PARAM());

		final WallPostResponseData response = //
			VkApiCall.callMethod("wall.get", clientParams, params, WallPostResponseData.class);
		return response;
	}

	public static SinglePhotoInfoResponse getPhotoInfo (final VkApiCallParams clientParams, final Long groupID, final Long photoID)
		throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("photos", groupID + "_" + photoID);
		final SinglePhotoInfoResponse response = //
			VkApiCall.callMethod("photos.getById", clientParams, params, SinglePhotoInfoResponse.class);

		return response;
	}

	public static CommentsResponseResult getComments (final VkApiCallParams clientParams, final Long groupID, final Long photoID)
		throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("type", "photo");
		params.put("photo_id", "" + photoID);
		params.put("need_likes", "1");
		params.put("owner_id", "" + groupID);
		params.put("access_key", "");
		final CommentsResponseResult response = //
			VkApiCall.callMethod("photos.getComments", clientParams, params, CommentsResponseResult.class);
		return response;
	}

	public static LikesResponseResult getLikes (final VkApiCallParams clientParams, final Long groupID, final Long photo_id,
		final boolean alsoRequestProfile) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("type", "photo");
		params.put("item_id", "" + photo_id);
		params.put("friends_only", "0");
		params.put("offset", "0");
		params.put("count", "10000000");
		params.put("owner_id", "" + groupID);
		final LikesResponseResult response = //
			VkApiCall.callMethod("likes.getList", clientParams, params, LikesResponseResult.class);
		return response;
	}

	public static PhotoDescriptionUpdateResponseResult updatePhotoDescription (final VkApiCallParams clientParams,
		final Long groupID, final Long photoID, final String newDescription) throws IOException {
		final Map<String, String> params = Collections.newMap();
		params.put("caption", newDescription);
		params.put("photo_id", "" + photoID);
		params.put("owner_id", "" + groupID);
		final PhotoDescriptionUpdateResponseResult response = //
			VkApiCall.callMethod("photos.edit", clientParams, params, PhotoDescriptionUpdateResponseResult.class);
		return response;
	}

}
