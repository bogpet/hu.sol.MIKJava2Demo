package hu.sol.java2survey.rest.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import hu.sol.java2survey.bean.Subject;

public interface SignUpApi {

	public ResponseEntity<HttpStatus> signUp(String name, String email, String subjectId);

	public ResponseEntity<List<Subject>> getSubjects();

}
