package com.oguzdirenc.notebook.security;


import com.oguzdirenc.notebook.domain.ApplicationUser;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.oguzdirenc.notebook.security.SecurityConstants.EXPIRATION_TIME;
import static com.oguzdirenc.notebook.security.SecurityConstants.SECRET;


@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication){
        ApplicationUser user =(ApplicationUser) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiredDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = user.getApplicationUserId().toString();

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",(user.getApplicationUserId().toString()));
        claims.put("username",user.getUsername());
        claims.put("fullName",user.getFullName());


        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512 , SECRET)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex ){
            System.out.println("Invalid secret key");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid token");
        }catch (ExpiredJwtException ex){
            System.out.println("Token expired");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported token");
        }catch (IllegalArgumentException ex){
            System.out.println("Token claims empty");
        }
        return false;
    }

    public String getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

        return (String)claims.get("id");
    }

}
