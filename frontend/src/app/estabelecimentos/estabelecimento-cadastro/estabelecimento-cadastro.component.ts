import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { MessageService } from 'primeng/api';

import { ErrorHandlerService } from '../../core/error-handler.service';
import { EstabelecimentoService } from '../estabelecimento.service';
import { Pessoa, Contato, Profissional, Estabelecimento } from '../../core/model';

@Component({
  selector: 'app-estabelecimento-cadastro',
  templateUrl: './estabelecimento-cadastro.component.html',
  styleUrls: ['./estabelecimento-cadastro.component.css']
})
export class EstabelecimentoCadastroComponent implements OnInit {

  estabelecimento:Estabelecimento = new Estabelecimento();
  estados: any[];
  cidades: any[];
  estadoSelecionado: number;

  constructor(
    private estabelecimentoService: EstabelecimentoService,
    private messageService: MessageService,
    private errorHandler: ErrorHandlerService,
    private route: ActivatedRoute,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    const codigoPessoa = this.route.snapshot.params['codigo'];

    this.title.setTitle('Novo Estabelecimento');



    if (codigoPessoa) {
      this.carregarEstabelecimento(codigoPessoa);
    }
  }

  get editando() {
    return Boolean(this.estabelecimento.codigo)
  }

  carregarEstabelecimento(codigo: number) {
    this.estabelecimentoService.buscarPorCodigo(codigo)
      .then(estabelecimento => {
        this.estabelecimento = estabelecimento;

        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizarEstabelecimento(form);
    } else {
      this.adicionarEstabelecimento(form);
    }
  }

  adicionarEstabelecimento(form: FormControl) {
    this.estabelecimentoService.adicionar(this.estabelecimento)
      .then(pessoaAdicionada => {
        this.messageService.add({ severity: 'success', detail: 'Estabelecimento adicionada com sucesso!' });
        this.router.navigate(['/estabelecimentos', pessoaAdicionada.codigo]);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  atualizarEstabelecimento(form: FormControl) {
    this.estabelecimentoService.atualizar(this.estabelecimento)
      .then(estabelecimento => {
        this.estabelecimento = estabelecimento;

        this.messageService.add({ severity: 'success', detail: 'Estabelecimento alterada com sucesso!' });
        this.atualizarTituloEdicao();
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  nova(form: FormControl) {
    form.reset();

    setTimeout(function() {
      this.pessoa = new Pessoa();
    }.bind(this), 1);

    this.router.navigate(['/estabelecimentos/nova']);
  }

  atualizarTituloEdicao() {
    this.title.setTitle(`Edição de pessoa: ${this.estabelecimento.nome}`);
  }

}
