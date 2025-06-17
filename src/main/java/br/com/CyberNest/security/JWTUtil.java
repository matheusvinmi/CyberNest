package br.com.CyberNest.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {
    private final String SECRET_KEY = "4V5PViJVI3a3NcZ8xN1a2toucf139P1ytMTrd5uL6kE=";
    private final long EXPIRATION_MS = 1000 * 60 * 60 * 4;

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String gerarToken(UUID idUsuario, String email){
        return Jwts.builder()
                .setSubject(email)
                .claim("id", idUsuario.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (JwtException exception){
            return false;
        }
    }

    public String getEmailDoToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token) // CORRIGIDO AQUI
                .getBody().getSubject();
    }
}