
package com.jfixby.vk.api.data.wall;

import java.util.ArrayList;

import com.jfixby.vk.api.data.comments.PostAttachment;

public class WallPost {
	public Long id;
	public Long from_id;
	public Long owner_id;
	public Long created_by;
	public Integer marked_as_ads;
	public String post_type;
	public String text;
	public Integer can_edit;
	public Integer can_publish;
	public Integer can_delete;

	public Boolean is_favorite;

	public WallPostComments comments = new WallPostComments();;

	public ArrayList<PostAttachment> attachments = new ArrayList<>();
}
