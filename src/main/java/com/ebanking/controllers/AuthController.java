package com.ebanking.controllers;

import javax.validation.Valid;

import com.ebanking.dao.AgentRepository;
import com.ebanking.dao.ClientRepository;
import com.ebanking.models.Agent;
import com.ebanking.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.dao.UserRepository;
import com.ebanking.models.User;
import com.ebanking.payload.request.LoginRequest;
import com.ebanking.payload.request.SignupRequest;
import com.ebanking.payload.response.JwtResponse;
import com.ebanking.payload.response.MessageResponse;
import com.ebanking.security.jwt.JwtUtils;
import com.ebanking.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
 
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getRole()
												 
												 ));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

	 
 
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getRole(),
							 encoder.encode(signUpRequest.getPassword()));


		userRepository.save(user);
		if(signUpRequest.getRole()== UserRole.agent){
			Agent agent=new Agent(user);
			agentRepository.save(agent);

		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
