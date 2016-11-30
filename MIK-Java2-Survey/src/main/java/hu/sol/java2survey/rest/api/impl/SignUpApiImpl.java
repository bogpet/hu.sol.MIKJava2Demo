package hu.sol.java2survey.rest.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hu.sol.java2survey.bean.Subject;
import hu.sol.java2survey.rest.api.SignUpApi;
import hu.sol.java2survey.service.SurveyService;

@CrossOrigin
@RestController
public class SignUpApiImpl implements SignUpApi {

	@Autowired
	private SurveyService surveyService;

	@Override
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> signUp(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("subjectId") String subjectId) {

		try {
			this.surveyService.saveStudent(name, email, subjectId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	@RequestMapping(value = "/subjects", method = RequestMethod.GET)
	public ResponseEntity<List<Subject>> getSubjects() {
		try {
			List<Subject> subjects = this.surveyService.getSubjects();
			return new ResponseEntity<>(subjects, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
