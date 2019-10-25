
package com.jfixby.vk.api.data.comments;

import java.util.ArrayList;

public class Comment {
	public Long id;
	public Long from_id;
	public ArrayList<Long> parents_stack = new ArrayList<>();
	public Long date;
	public String text;
	public Long reply_to_user;
	public Long reply_to_comment;
	public CommentLikes likes;
	public ArrayList<PostAttachment> attachments = new ArrayList<>();
}
