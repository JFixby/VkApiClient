
package com.jfixby.vk.api.data.album;

import java.util.ArrayList;

public class VKAlbumPhotoData {
	public Long id;
	public Long album_id;
	public Long owner_id;
	public Long user_id;
	public String text;
	public Long date;
	public ArrayList<VKAlbumPhotoDataSize> sizes = new ArrayList<>();
}
