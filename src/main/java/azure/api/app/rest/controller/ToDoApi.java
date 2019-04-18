package azure.api.app.rest.controller;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import azure.api.app.model.TodoItem;

@RestController
@RequestMapping(value = "/api/todo")
@CrossOrigin(origins = "*")
public class ToDoApi {

	private final Log logger = LogFactory.getLog(ToDoApi.class);
	private TodoItem item;

	@GetMapping("/get")
	private ResponseEntity<TodoItem> get() {
		logger.info("Fetching data.....");
		if (!Objects.equals(item, null)) {
			return new ResponseEntity<TodoItem>(item, HttpStatus.OK);
		} else {
			return new ResponseEntity<TodoItem>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/post")
	private void Post(@RequestBody TodoItem item) {
		logger.info("Updating data.....");
		if (!Objects.equals(item, null)) {
			this.item.setId(item.getId());
			this.item.setName(item.getName());
			this.item.setComplete(item.isComplete());
		}
	}

	@PutMapping("/put")
	private void Put(@RequestBody TodoItem item) {
		logger.info("Creating data.....");
		this.item = new TodoItem();
		this.item.setId(item.getId());
		this.item.setName(item.getName());
		this.item.setComplete(item.isComplete());
	}

	@DeleteMapping("/delete")
	private void Delete() {
		logger.info("Deleting data.....");
		this.item = null;
	}
}
