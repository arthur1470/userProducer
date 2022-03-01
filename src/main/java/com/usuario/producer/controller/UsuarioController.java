package com.usuario.producer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.producer.controller.dto.UsuarioDTO;
import com.usuario.producer.producer.GerarSenhaProducer;
import com.usuario.producer.producer.RegistroProducer;

import io.opentracing.Span;
import io.opentracing.Tracer;

@RestController()
@RequestMapping("/user")
public class UsuarioController {

	private final RegistroProducer registroProducer;
	private final GerarSenhaProducer gerarSenhaProducer;
	private Tracer tracer;
	

	public UsuarioController(RegistroProducer registroProducer, 
							 GerarSenhaProducer gerarSenhaProducer,
							 Tracer tracer) {
		
		this.registroProducer = registroProducer;
		this.gerarSenhaProducer = gerarSenhaProducer;
		this.tracer = tracer;
	}

	@PostMapping("/register")
	public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		Span span = tracer.activeSpan();
		
		span.setOperationName("REGISTRO: " + usuarioDTO.getCpf());
		span.setTag("CPF", usuarioDTO.getCpf());
		
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
}
