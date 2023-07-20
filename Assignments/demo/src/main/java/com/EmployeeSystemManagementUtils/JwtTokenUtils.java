//package com.EmployeeSystemManagementUtils;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@Component
//public class JwtTokenUtils {
//@Value("${jwt.secretkey}")
//	private String secretKey;
//@Value("${jwt.expiry")
//private long expiry;
//
//public String generateToken(String userDetails) {
//	Map<String,Object> claims= new HashMap<>();
//	return Jwts.builder()
//			.setClaims(claims)
//			.setSubject(userDetails)
//			.setIssuedAt(new Date())
//			.setExpiration(new Date(System.currentTimeMillis()+expiry*1000))
//			//.signWith(SignatureAlgorithm.HS512, secretKey)
//			.compact();
//}
//public Boolean isTokenExprired(String token) {
//	return extractExpiration(token).before(new Date());
//}
//public String extractUsername(String token) {
//	return extractClaim(token,Claims::getSubject);
//}
//public Date extractExpiration(String token) {
//	return extractClaim(token,Claims::getExpiration);
//	}
//public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
//	final Claims claims=extractAllClaims(token);
//	return claimsResolver.apply(claims);
//}
//private Claims extractAllClaims(String token) {
//	return Jwts.parser().setSigningKey(secretKey)
//			.parseClaimsJws(token).getBody();
//}
//
//}
