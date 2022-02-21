package com.usuario.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.producer.controller.dto.LoginDTO;
import com.usuario.producer.controller.dto.UsuarioDTO;
import com.usuario.producer.producer.GerarSenhaProducer;
import com.usuario.producer.producer.LoginProducer;
import com.usuario.producer.producer.RegistroProducer;

@RestController()
@RequestMapping("/user")
public class UsuarioController {

	private final RegistroProducer registroProducer;
	private final GerarSenhaProducer gerarSenhaProducer;
	private final LoginProducer loginProducer;

	public UsuarioController(RegistroProducer registroProducer, 
							 LoginProducer loginProducer,
							 GerarSenhaProducer gerarSenhaProducer) {
		
		this.registroProducer = registroProducer;
		this.loginProducer = loginProducer;
		this.gerarSenhaProducer = gerarSenhaProducer;
	}

	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		registroProducer.sendRegistrar(usuarioDTO);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/token/{cpf}")
	public ResponseEntity<Void> registrarUsuario(@PathVariable String cpf) {
		if(cpf.length() != 11) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		gerarSenhaProducer.sendGerarSenha(cpf);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginDTO> registrarUsuario(@RequestBody LoginDTO loginDTO) {
		loginProducer.sendLogar(loginDTO);
		
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
