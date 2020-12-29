import { Title } from '@angular/platform-browser';
import { Component, OnInit, ViewChild } from '@angular/core';

import { LazyLoadEvent, MessageService, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';

import { AuthService } from './../../seguranca/auth.service';
import { ErrorHandlerService } from './../../core/error-handler.service';
import { VinculoService, VinculoFiltro } from './../vinculos.service';

@Component({
  selector: 'app-vinculos-pesquisa',
  templateUrl: './vinculos-pesquisa.component.html',
  styleUrls: ['./vinculos-pesquisa.component.css']
})
export class VinculoPesquisaComponent implements OnInit {

  totalRegistros = 0;
  filtro = new VinculoFiltro();
  vinculos = [];
  @ViewChild('tabela') grid: Table;

  constructor(
    private vinculosService: VinculoService,
    public auth: AuthService,
    private errorHandler: ErrorHandlerService,
    private messageService: MessageService,
    private confirmation: ConfirmationService,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Pesquisa de vinculo');
  }

  pesquisar(pagina = 0) {
    this.filtro.pagina = pagina;

    this.vinculosService.pesquisar(this.filtro)
      .then(resultado => {
        this.totalRegistros = resultado.total;
        this.vinculos = resultado.vinculos;
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  aoMudarPagina(event: LazyLoadEvent) {
    const pagina = event.first / event.rows;
    this.pesquisar(pagina);
  }

  confirmarExclusao(lancamento: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.excluir(lancamento);
      }
    });
  }

  excluir(lancamento: any) {
    this.vinculosService.excluir(lancamento.codigo)
      .then(() => {
        if (this.grid.first === 0) {
          this.pesquisar();
        } else {
          this.grid.reset();
        }

        this.messageService.add({ severity: 'success', detail: 'Vinculo excluído com sucesso!' });
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
