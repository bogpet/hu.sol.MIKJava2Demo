package hu.sol.java2survey.rest.api.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sol.java2survey.rest.api.SignUpApi;
import hu.sol.java2survey.service.StudentService;

@CrossOrigin
@RestController
public class SignUpApiImpl implements SignUpApi {

	@Autowired
	private StudentService studentService;

	@Override
	@RequestMapping(value = "/sign", method = RequestMethod.GET)
	public ResponseEntity<HttpStatus> signUp(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("subjectId") String subjectId) {

		try {
			this.studentService.saveStudent(name, email, subjectId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
