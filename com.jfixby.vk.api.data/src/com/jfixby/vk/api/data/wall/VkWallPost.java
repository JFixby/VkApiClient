
package com.jfixby.vk.api.data.wall;

import java.util.ArrayList;

import com.jfixby.vk.api.data.comments.VkPostAttachment;

public class VkWallPost {
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

	public VkWallPostComments comments = new VkWallPostComments();;

	public ArrayList<VkPostAttachment> attachments = new ArrayList<>();
}
