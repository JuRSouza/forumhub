package br.com.alura.forumhub.infra.security;

import br.com.alura.forumhub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + 1000 * 60 * 60 * 2); // Token válido por 2 horas

        SecretKey chave = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder()
                .setIssuer("ForumHub API")                   // Quem está gerando
                .setSubject(usuario.getEmail())     // coloquei email
                .setIssuedAt(agora)                          // Quando gerado
                .setExpiration(expiracao)                    // Expiração
                .signWith(chave, SignatureAlgorithm.HS256)   // Algoritmo de assinatura
                .compact();
    }

    public String getSubject(String tokenJWT) {
        SecretKey chave = Keys.hmacShaKeyFor(secret.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(chave)
                .build()
                .parseClaimsJws(tokenJWT)
                .getBody()
                .getSubject();
    }


}
