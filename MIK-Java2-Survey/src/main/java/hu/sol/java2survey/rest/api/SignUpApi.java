package hu.sol.java2survey.rest.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface SignUpApi {

	public ResponseEntity<HttpStatus> signUp(String name, String email, String subjectId);

}
