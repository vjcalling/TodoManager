package com.learning.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.learning.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	private TodoService todoService;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {

		model.put("todos", todoService.retrieveTodos(retrieveLoggedInUserName()));
		return "list-todos";
	}


	
	private String retrieveLoggedInUserName() {

		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();
		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
//		throw new RuntimeException("Dummy Exception");
		model.addAttribute("todo",new Todo());//(0,"in28Minutes","",new Date(), false));
		model.put("operation","Add a Todo");
		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		
		if(result.hasErrors()){
			return "todo";
		}else{
			todoService.save(todo);
		}
		todoService.addTodo(retrieveLoggedInUserName(), todo.getDesc(), new Date(), false);
		model.clear();		//to remove any query parameter to be passed as part of re-direct.
		return "redirect:list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		//Delete Todo

		todoService.deleteTodo(id);
		model.clear();		//to remove any query parameter to be passed as part of re-direct.
		return "redirect:list-todos";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {
		//Delete Todo

		Todo todo = todoService.retrieveTodo(id);
		model.addAttribute("todo",todo);
		model.put("operation","Edit a Todo");
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result) {
		//update todo here
		
		if(result.hasErrors()){
			return "todo";
		}
		todo.setUser(retrieveLoggedInUserName());
		todoService.updateTodo(todo);
		return "redirect:list-todos";
	}
	
		

}
