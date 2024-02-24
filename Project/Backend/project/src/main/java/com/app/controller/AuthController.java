package com.app.controller;

import org.apache.catalina.connector.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Exception.UserException;
import com.app.config.JwtProvider;
import com.app.model.Cart;
import com.app.model.User;
import com.app.repositry.UserRepository;
import com.app.request.LoginRequest;
import com.app.response.AuthResponse;
import com.app.service.CartService;
import com.app.service.CustomeUserServiceImplementation;
import com.app.service.UserService;
import com.app.service.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomeUserServiceImplementation customeUserServiceImplementation;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	public AuthController( UserRepository  UserRepository, CustomeUserServiceImplementation customUserServiceImplementation
			,PasswordEncoder passwordEncoder,  JwtProvider jwtProvider, CartService cartService,UserService userService) {
		this.userRepository = userRepository;
		this.customeUserServiceImplementation = customeUserServiceImplementation;
		this.jwtProvider = jwtProvider;
		this.passwordEncoder = passwordEncoder;
		this.cartService  = cartService;
		this.userService = userService;
	}
	
	@CrossOrigin("*")
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> creatUserHandler(@RequestBody User user) throws UserException{
		int userId = user.getId();
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		String contact = user.getContact();
		String role = user.getRole();
		User isEmailExist = userRepository.findByEmail(email);
		System.out.println(email);
		
		if(isEmailExist !=null) {
			throw new UserException("Email is Already Used With Another Account");
		}
		
		User creatdedUser = new User();
		creatdedUser.setEmail(email);
		creatdedUser.setPassword(passwordEncoder.encode(password));
		creatdedUser.setFirstName(firstName);
		creatdedUser.setLastName(lastName);
		creatdedUser.setContact(contact);
		creatdedUser.setRole(role);
		User savedUser = userRepository.save(creatdedUser);
		
		Cart cart = cartService.createCart(savedUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage( "Signup Success");
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}
	
	@CrossOrigin("*")
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException{
		String username = loginRequest.getEmail();
		System.out.println(username);
		String password = loginRequest.getPassword();
		
		
		Authentication authetication = authenticate(username,password);
		
		SecurityContextHolder.getContext().setAuthentication(authetication);
		
        String token = jwtProvider.generateToken(authetication);
       User user = userService.findUserByEmail(username);
		String role = user.getRole();
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage(role);
		
		return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
	}

	private Authentication authenticate(String username, String password) {
		System.out.println("authenticate"+ username);
		UserDetails userDetails = customeUserServiceImplementation.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Invalid Username!!");		}
		
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password!!!");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
}
