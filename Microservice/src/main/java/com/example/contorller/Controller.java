package com.example.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Rest API service", description = "Crud operations are performed")
@RestController
@RequestMapping("/studentcontroller")
public class Controller {
	@Autowired
	private UserService userService;

	@Operation(summary = "Get API rest method", description = "Create student RestAPI is used to store in the database")
	@ApiResponse(responseCode = "201", description = "Http Status 201 is created")
	@GetMapping("/hello")
	public String HelloWorld() {
		return "hello world";
	}

	@GetMapping("/personal/{id}/{first-name}")
	public String Hi(@PathVariable("id") int studentId, @PathVariable("first-name") String name) {
		return "Hello " + name + " my age is " + studentId;

	}

	@GetMapping("/personal1/query")
	public String queryparametermethod(@RequestParam String id) {
		return "Hello " + id;

	}

	@GetMapping("/personal2/multiplequery")
	public String multiplequeryparametermethod(@RequestParam String firstname, @RequestParam() String lastname) {
		return "FullName is " + firstname + " " + lastname;

	}

	@PostMapping("/create")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addStudent(@RequestBody @Valid User user) {
		User u = userService.createUser(user);
		return ResponseEntity.ok().header("customer", "ramesh").body(u);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateStudent(@PathVariable int id, @RequestBody User user) {
		user.setId(id);
		User u = userService.updateUser(user);
		return ResponseEntity.ok(u);
	}

}
