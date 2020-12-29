import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { environment } from './../../environments/environment';
import { Pessoa, Estado, Cidade, Profissional, Estabelecimento } from './../core/model';

export class EstabelecimentoFiltro {
  nome: string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable()
export class EstabelecimentoService {

  estabelecimentosUrl: string;
  cidadesUrl: string;
  estadosUrl: string;

  constructor(private http: HttpClient) {
    this.estabelecimentosUrl = `${environment.apiUrl}/estabelecimentos`;

  }

  pesquisar(filtro: EstabelecimentoFiltro): Promise<any> {
    let params = new HttpParams()
      .set('page', filtro.pagina.toString())
      .set('size', filtro.itensPorPagina.toString());

    if (filtro.nome) {
      params = params.set('nome', filtro.nome);
    }

    return this.http.get(`${this.estabelecimentosUrl}`, { params })
      .toPromise()
      .then(response => {
        const pessoas = response['content'];

        const resultado = {
          pessoas,
          total: response['totalElements']
        };

        return resultado;
      })
  }

  listarTodas(): Promise<any> {
    return this.http.get(this.estabelecimentosUrl)
      .toPromise()
      .then(response => response['content']);
  }

  excluir(codigo: number): Promise<void> {
    return this.http.delete(`${this.estabelecimentosUrl}/${codigo}`)
      .toPromise()
      .then(() => null);
  }



  adicionar(estabecimento: Estabelecimento): Promise<Estabelecimento> {
    return this.http.post<Estabelecimento>(this.estabelecimentosUrl, estabecimento)
      .toPromise();
  }

  atualizar(estabecimento: Estabelecimento): Promise<Estabelecimento> {
    return this.http.put<Estabelecimento>(`${this.estabelecimentosUrl}/${estabecimento.codigo}`, estabecimento)
      .toPromise();
  }

  buscarPorCodigo(codigo: number): Promise<Estabelecimento> {
    return this.http.get<Estabelecimento>(`${this.estabelecimentosUrl}/${codigo}`)
      .toPromise();
  }





}
