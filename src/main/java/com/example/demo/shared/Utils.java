package com.example.demo.shared;

import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final String CODE_ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_";
	
    public String generateSerialNumber(int length) {
        return generateRandomString(length,ALPHABET);
    }
    
    public String generateCode(int length) {
        return generateRandomString(length,CODE_ALPHABET);
    }
    
    public boolean validateMedicationName(String name) {
    	Pattern p = Pattern.compile("^[A-Za-z0-9_-]*$", Pattern.CASE_INSENSITIVE);
    	Matcher m = p.matcher(name);
    	boolean valid = m.find();
    	return valid;
    	
    }
    
    private String generateRandomString(int length,String alphabet) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }
    
//	public static boolean hasTokenExpired(String token) {
//		boolean returnValue = false;
//
//		try {
//			Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
//					.getBody();
//
//			Date tokenExpirationDate = claims.getExpiration();
//			Date todayDate = new Date();
//
//			returnValue = tokenExpirationDate.before(todayDate);
//		} catch (ExpiredJwtException ex) {
//			returnValue = true;
//		}
//
//		return returnValue;
//	}

//    public String generateEmailVerificationToken(String userId) {
//        String token = Jwts.builder()
//                .setSubject(userId)
//                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
//                .compact();
//        return token;
//    }
//    
//    public String generatePasswordResetToken(String userId)
//    {
//        String token = Jwts.builder()
//                .setSubject(userId)
//                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
//                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
//                .compact();
//        return token;
//    }
}
