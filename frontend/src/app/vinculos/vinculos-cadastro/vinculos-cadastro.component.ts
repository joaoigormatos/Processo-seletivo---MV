import { EstabelecimentoService } from './../../estabelecimentos/estabelecimento.service';
import { Title } from '@angular/platform-browser';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { MessageService } from 'primeng/api';

import { ErrorHandlerService } from './../../core/error-handler.service';
import { CategoriaService } from './../../categorias/categoria.service';
import { PessoaService } from './../../pessoas/pessoa.service';
import { Lancamento, Profissional } from './../../core/model';
import { VinculoService } from './../vinculos.service';

@Component({
  selector: 'app-lancamento-cadastro',
  templateUrl: './vinculos-cadastro.component.html',
  styleUrls: ['./vinculos-cadastro.component.css']
})
export class VinculoCadastroComponent implements OnInit {


  estabelecimentos = [];
  profissional = [];
  // lancamento = new Lancamento();
  formulario: FormGroup;
  uploadEmAndamento = false;

  constructor(
    private estabelecimentoServiceService: EstabelecimentoService,
    private profissionalService: PessoaService,
    private vinculoService: VinculoService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit() {
    this.configurarFormulario();

    const codigoLancamento = this.route.snapshot.params['codigo'];

    this.title.setTitle('Novo Vínculo');

    if (codigoLancamento) {
      this.carregarVinculos(codigoLancamento);
    }

    this.carregarEstabelecimentos();
    this.carregarProfissionais();
  }



  aoTerminarUploadAnexo(event) {
    const anexo = event.originalEvent.body;

    this.formulario.patchValue({
      anexo: anexo.nome,
      urlAnexo: anexo.url
    });

    this.uploadEmAndamento = false;
  }









  configurarFormulario() {
    this.formulario = this.formBuilder.group({
      codigo: [],
      profissional: this.formBuilder.group({
        codigo: [ null, Validators.required ],
        nome: []
      }),
      estabelecimento: this.formBuilder.group({
        codigo: [ null, Validators.required ],
        nome: []
      }),

    });
  }

  validarObrigatoriedade(input: FormControl) {
    return (input.value ? null : { obrigatoriedade: true });
  }

  validarTamanhoMinimo(valor: number) {
    return (input: FormControl) => {
      return (!input.value || input.value.length >= valor) ? null : { tamanhoMinimo: { tamanho: valor } };
    };
  }

  get editando() {
    return Boolean(this.formulario.get('codigo').value);
  }

  carregarVinculos(codigo: number) {
    this.vinculoService.buscarPorCodigo(codigo)
      .then(vinculos => {
        // this.lancamento = lancamento;
        this.formulario.patchValue(vinculos);
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar() {
    if (this.editando) {
      this.atualizarVinculo();
    } else {
      this.adicionarVinculo();
    }
  }

  adicionarVinculo() {
    this.vinculoService.adicionar(this.formulario.value)
      .then(lancamentoAdicionado => {
        this.messageService.add({ severity: 'success', detail: 'Vinculo adicionado com sucesso!' });

        // form.reset();
        // this.lancamento = new Lancamento();
        this.router.navigate(['/lancamentos', lancamentoAdicionado.codigo]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarVinculo() {
    this.vinculoService.atualizar(this.formulario.value)
      .then(lancamento => {
        // this.lancamento = lancamento;
        this.formulario.patchValue(lancamento);

        this.messageService.add({ severity: 'success', detail: 'Vinculo alterado com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarEstabelecimentos() {
    return this.estabelecimentoServiceService.listarTodas()
      .then(estabelecimentos => {
        this.estabelecimentos = estabelecimentos
          .map(c => ({ label: c.nome, value: c.codigo }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  carregarProfissionais() {
    this.profissionalService.listarTodas()
      .then(proficionais => {
        this.profissional = proficionais
          .map(p => ({ label: p.nome, value: p.codigo }));
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  novo() {
    this.formulario.reset();

    setTimeout(function() {
      this.lancamento = new Lancamento();
    }.bind(this), 1);

    this.router.navigate(['/vinculos/novo']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de vinculo: ${this.formulario.get('descricao').value}`);
  }

}
