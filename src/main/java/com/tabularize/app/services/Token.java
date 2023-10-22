package com.tabularize.app.services;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.tabularize.app.exception.CustomException;

@Service
public class Token {
	
	private static final String TOKEN_SECRET = "secret";

	public String createToken(String email) {
		try {
			//to set algorithm
			Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

			String token = JWT.create()
					.withClaim("user_id", email)
					.sign(algorithm);
			return token;
		} catch (JWTCreationException e) {
			e.printStackTrace();
			throw new CustomException("Failed To create Token");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new CustomException("Internal Server Error", e);
		}
	}
	
	public String decodeToken(String token) {
		
		String user_id;
		//verification algorithm
		Verification verification = null;
		try {
			verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
		
		JWTVerifier jwtVerifire = verification.build();
		//to decode
		DecodedJWT decodedJwt = jwtVerifire.verify(token);
		
		
		Claim claim = decodedJwt.getClaim("user_id");
			
		user_id = claim.asString();
		
		return user_id;
	}
}