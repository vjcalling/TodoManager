package com.learning.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.learning.todo.Todo;

@Repository("todoRepository")
public class TodoRepositoryImpl implements TodoRepository{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Todo save(Todo todo) {
		em.persist(todo);
		em.flush();
		return todo;
	}

}
