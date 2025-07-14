package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.usuario.DadosAutenticacao;
import br.com.alura.forumhub.domain.usuario.DadosTokenJWT;
import br.com.alura.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> autenticar(@RequestBody @Valid DadosAutenticacao form) {
        var dadosLogin = new UsernamePasswordAuthenticationToken(form.email(), form.senha());
        Authentication authentication = authManager.authenticate(dadosLogin);
        String token = tokenService.gerarToken(authentication);
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}
