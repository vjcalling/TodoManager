package com.learning.service;

import java.util.Date;
import java.util.List;

import com.learning.todo.Todo;

public interface TodoService {

	Todo save(Todo todo);

	List<Todo> retrieveTodos(String retrieveLoggedInUserName);

	void addTodo(String retrieveLoggedInUserName, String desc, Date date, boolean b);

	void deleteTodo(int id);

	Todo retrieveTodo(int id);

	void updateTodo(Todo todo);
	
}
