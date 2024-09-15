package br.com.fiap.insights.controller;

import br.com.fiap.insights.dto.autenticacao.LoginRequestDTO;
import br.com.fiap.insights.dto.autenticacao.RegisterRequestDTO;
import br.com.fiap.insights.dto.autenticacao.ResponseDTO;
import br.com.fiap.insights.dto.cliente.DetalhesCliente;
import br.com.fiap.insights.model.Cliente;
import br.com.fiap.insights.repository.ClienteRepository;
import br.com.fiap.insights.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "registra e logar", description = "Operações para registra e logar um cliente")
public class AuthController {

        private final ClienteRepository clienteRepository;
        private final PasswordEncoder passwordEncoder;
        private final TokenService tokenService;

        @PostMapping("/login")
        @Operation(description = "Logar usando informações da Cliente", summary = "Logar usando informações do Cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "login realizado com secesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da cliente que terá as informações registradas", required = true)
        })
        public ResponseEntity login(@RequestBody LoginRequestDTO body) {
                Cliente cliente = this.clienteRepository.findByLogin(body.login())
                                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
                if (passwordEncoder.matches(body.password(), cliente.getPassword())) {
                        String token = this.tokenService.generateToken(cliente);
                        return ResponseEntity.ok(new ResponseDTO(cliente.getId(), cliente.getNome(), token));
                }
                return ResponseEntity.badRequest().build();
        }

        @PostMapping("/register")
        @Operation(description = "Registra informações na Cliente", summary = "Registra informações do Cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Registro realizado com secesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da Cliente que terá as informações registradas", required = true)
        })
        public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
                Optional<Cliente> cliente = this.clienteRepository.findByLogin(body.login());

                if (cliente.isEmpty()) {
                        Cliente newCliente = new Cliente();
                        newCliente.setPassword(passwordEncoder.encode(body.password()));
                        newCliente.setLogin(body.login());
                        newCliente.setNome(body.nome());
                        this.clienteRepository.save(newCliente);

                        String token = this.tokenService.generateToken(newCliente);
                        return ResponseEntity.ok(new ResponseDTO(newCliente.getId(), newCliente.getNome(), token));
                }
                return ResponseEntity.badRequest().build();
        }
}
