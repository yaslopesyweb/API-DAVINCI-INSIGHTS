package br.com.fiap.insights.controller;

import br.com.fiap.insights.dto.opiniao.DetalhesOpiniao;
import br.com.fiap.insights.dto.produto.AtualizarProduto;
import br.com.fiap.insights.dto.produto.CadastrarProduto;
import br.com.fiap.insights.dto.produto.DetalhesProduto;
import br.com.fiap.insights.dto.produto.ListagemProduto;
import br.com.fiap.insights.model.Produto;
import br.com.fiap.insights.repository.ProdutoRepository;
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
@RequestMapping("produto")
@Slf4j
@Tag(name = "Produtos ", description = "Operações para manipular os produtos")
public class ProdutoController {

        @Autowired
        ProdutoRepository produtoRepository;

        @PostMapping
        @Transactional
        @Operation(description = "Cadastra informações no Produto", summary = "Cadastro de informações")
        @ApiResponses({
                        @ApiResponse(responseCode = "200", description = "Produto cadastrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesProduto.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado")

        })
        @Parameters({
                        @Parameter(name = "id", description = "Id do produto que terá as informações cadastrado", required = true)
        })

        public ResponseEntity<DetalhesProduto> post(@RequestBody @Valid CadastrarProduto dto,
                        UriComponentsBuilder uriBuilder) {
                var produto = new Produto(dto);
                produtoRepository.save(produto);
                var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(new DetalhesProduto(produto));

        }

        @DeleteMapping("{id}")
        @Transactional
        @Hidden
        @Operation(description = "Deletar informações no Produto", summary = "Deletar informações do produto")
        @ApiResponses({
                        @ApiResponse(responseCode = "204", description = "informações Deletadas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesProduto.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "id", description = "Id da produto que terá as informações deletadas", required = true)
        })
        public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
                produtoRepository.deleteById(id);
                return ResponseEntity.noContent().build();

        }

        @PutMapping("{id}")
        @Transactional
        @Operation(summary = "Atualizar informações do Produto", description = "Atualizar informações existente do produto")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "informações atualizadas com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesProduto.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
        })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do produto que terá as informações atualizadas", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<DetalhesProduto> put(@PathVariable("id") Long id,
                        @RequestBody AtualizarProduto dto) {
                var produto = produtoRepository.getReferenceById(id);
                produto.atualizarProduto(dto);
                return ResponseEntity.ok(new DetalhesProduto(produto));

        }

        @GetMapping("{id}")
        @Operation(summary = "Visualizar informações do Produto", description = "Visualizar informações existente do produto")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Produto visualizado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesProduto.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do produto que terá o informações visualizado", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<DetalhesProduto> get(@PathVariable("id") Long id) {
                var produto = produtoRepository.getReferenceById(id);
                return ResponseEntity.ok(new DetalhesProduto(produto));

        }

        @GetMapping
        @Operation(summary = "Recuperar informações do Produto", description = "Recuperar informações existente do produto")
        @ApiResponses({ @ApiResponse(responseCode = "200", description = "Produto recuperada com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DetalhesProduto.class))),
                        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
                        @ApiResponse(responseCode = "404", description = "informações não encontrado") })
        @Parameters({
                        @Parameter(name = "idPost", description = "Id do produto que terá o informações recuperada", required = true, in = ParameterIn.PATH),
        })
        public ResponseEntity<List<ListagemProduto>> list(Pageable pageable) {
                var ListaDto = produtoRepository.findAll().stream().map(ListagemProduto::new).toList();
                return ResponseEntity.ok(ListaDto);
        }
}