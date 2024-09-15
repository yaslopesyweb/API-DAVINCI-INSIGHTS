package br.com.fiap.insights.controller;

import br.com.fiap.insights.dto.cliente.AtualizarCliente;
import br.com.fiap.insights.dto.cliente.DetalhesCliente;
import br.com.fiap.insights.dto.cliente.ListagemCliente;
import br.com.fiap.insights.dto.opiniao.DetalhesOpiniao;
import br.com.fiap.insights.dto.produto.DetalhesProduto;
import br.com.fiap.insights.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/cliente")
@Slf4j
@Tag(name = "Cliente", description = "Operações para manipular os Cliente ")
public class ClienteController {

        @Autowired
        private ClienteRepository clienteRepository;

        @DeleteMapping("{id}")
        @Transactional
        @Operation(description = "Deletar informações na Cliente", summary = "Deletar informações do Cliente")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "informações Deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da cliente que terá as informações deletado", required = true)
        })
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
                clienteRepository.deleteById(id);
                return ResponseEntity.noContent().build();

        }

        @PutMapping("{id}")
        @Transactional
        @Operation(summary = "Atualizar informações no Cliente", description = "Atualizar informações existente ao um Cliente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "informações atualizadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))) })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do cliente que terá as informações atualizadas", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<DetalhesCliente> put(@PathVariable("id") Long id,
                        @RequestBody AtualizarCliente dto) {
                var cliente = clienteRepository.getReferenceById(id);
                cliente.atualizarCliente(dto);
                return ResponseEntity.ok(new DetalhesCliente(cliente));

        }

        @GetMapping("{id}")
        @Operation(summary = "Visualizar informações do Cliente", description = "Visualizar informações existente ao um Cliente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Cliente visualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do cliente que terá o informações visualizado", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<ListagemCliente> get(@PathVariable("id") Long id) {
                var cliente = clienteRepository.getReferenceById(id);
                return ResponseEntity.ok(new ListagemCliente(cliente));

        }

        @GetMapping
        @Operation(summary = "Recuperar informações do Cliente", description = "Recuperar informações existente ao um Cliente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Cliente recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesCliente.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do cliente que terá o informações recuperada", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<List<ListagemCliente>> list(Pageable pageable) {
                var ListaDto = clienteRepository.findAll().stream().map(ListagemCliente::new).toList();
                return ResponseEntity.ok(ListaDto);
        }
}
