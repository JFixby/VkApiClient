
package com.jfixby.vk.api.data.comments;

import java.util.ArrayList;

public class VkComment {
	public Long id;
	public Long from_id;
	public ArrayList<Long> parents_stack = new ArrayList<>();
	public Long date;
	public String text;
	public Long reply_to_user;
	public Long reply_to_comment;
	public VkCommentLikes likes;
	public ArrayList<VkPostAttachment> attachments = new ArrayList<>();
}
