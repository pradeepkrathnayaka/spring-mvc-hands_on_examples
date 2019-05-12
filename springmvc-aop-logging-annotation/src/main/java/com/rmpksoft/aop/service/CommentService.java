package com.rmpksoft.aop.service;

import com.rmpksoft.aop.model.Comment;

public interface CommentService {
	
	public void save(String msg) throws Exception;

	public Comment getCommentById(long id) throws Exception;

	public void delete(long id) throws Exception;

}
