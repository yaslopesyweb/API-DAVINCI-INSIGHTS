package br.com.fiap.insights.controller;

import br.com.fiap.insights.dto.opiniao.AtualizarOpiniao;
import br.com.fiap.insights.dto.opiniao.CadastrarOpiniao;
import br.com.fiap.insights.dto.opiniao.DetalhesOpiniao;
import br.com.fiap.insights.dto.opiniao.ListagemOpiniao;
import br.com.fiap.insights.dto.produto.DetalhesProduto;
import br.com.fiap.insights.model.Opiniao;
import br.com.fiap.insights.repository.OpiniaoRepository;
import io.swagger.v3.oas.annotations.Hidden;
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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("opiniao")
@Slf4j
@Tag(name = "Opinião do cliente", description = "Operações para análise de feedbacks de clientes")
public class OpiniaoController {

        @Autowired
        OpiniaoRepository opiniaoRepository;

        @PostMapping
        @Transactional
        @Operation(description = "Cadastra um comentário na opinião", summary = "Cadastro de comentário")
        @ApiResponses({
                        @ApiResponse(responseCode = "201", description = "Comentário cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesOpiniao.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Comentário não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da opinião que terá o comentário cadastrado", required = true)
        })
        public ResponseEntity<DetalhesOpiniao> post(@RequestBody @Valid CadastrarOpiniao dto,
                        UriComponentsBuilder uriBuilder) {
                var opiniao = new Opiniao(dto);
                opiniaoRepository.save(opiniao);
                var uri = uriBuilder.path("/opiniao/{id}").buildAndExpand(opiniao.getId()).toUri();
                return ResponseEntity.created(uri).body(new DetalhesOpiniao(opiniao));

        }

        @DeleteMapping("{id}")
        @Transactional
        @Hidden
        @Operation(description = "Deletar um comentário", summary = "Deletar de um comentário")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "Comentário Deletado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesOpiniao.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Comentário não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da opinião que terá o comentário deletado", required = true)
        })
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
                opiniaoRepository.deleteById(id);
                return ResponseEntity.noContent().build();

        }

        @PutMapping("{id}")
        @Transactional
        @Operation(summary = "Atualizar um comentário", description = "Atualizar um comentário existente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Opinião Atualizar com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesOpiniao.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Comentário não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do opinião que terá a comentário atualizado", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<DetalhesOpiniao> put(@PathVariable("id") Long id,
                        @RequestBody AtualizarOpiniao dto) {
                var opiniao = opiniaoRepository.getReferenceById(id);
                opiniao.atualizarOpiniao(dto);
                return ResponseEntity.ok(new DetalhesOpiniao(opiniao));

        }

        @GetMapping("{id}")
        @Operation(summary = "Visualizar um comentário", description = "Visualizar um comentário existente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Opinião visualizada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesOpiniao.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Comentário não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do opinião que terá o comentário visualizado", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<DetalhesOpiniao> get(@PathVariable("id") Long id) {
                var opiniao = opiniaoRepository.getReferenceById(id);
                return ResponseEntity.ok(new DetalhesOpiniao(opiniao));

        }

        @GetMapping
        @Operation(summary = "Recuperar uma Opinião do Produto", description = "Recuperar uma opinião existente")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Opinião recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesOpiniao.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Comentário não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do produto que terá o opinião recuperada", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<List<ListagemOpiniao>> list(Pageable pageable) {
                var ListaDto = opiniaoRepository.findAll().stream().map(ListagemOpiniao::new).toList();
                return ResponseEntity.ok(ListaDto);
        }
}