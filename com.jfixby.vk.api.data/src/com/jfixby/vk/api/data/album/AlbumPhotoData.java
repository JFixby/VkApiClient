
package com.jfixby.vk.api.data.album;

import java.util.ArrayList;

public class AlbumPhotoData {
	public Long id;
	public Long album_id;
	public Long owner_id;
	public Long user_id;
	public String text;
	public Long date;
	public ArrayList<AlbumPhotoDataSize> sizes = new ArrayList<>();
}
