import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';


import { environment } from './../../environments/environment';
import { Lancamento } from './../core/model';

export class VinculoFiltro {

  profissional:string;
  pagina = 0;
  itensPorPagina = 5;
}

@Injectable()
export class VinculoService {

  vinculosUrl: string;

  constructor(private http: HttpClient) {
    this.vinculosUrl = `${environment.apiUrl}/vinculos`;
  }



  pesquisar(filtro: VinculoFiltro): Promise<any> {
    let params = new HttpParams()
      .set('page', filtro.pagina.toString())
      .set('size', filtro.itensPorPagina.toString());




    return this.http.get(`${this.vinculosUrl}?resumo`, { params })
      .toPromise()
      .then(response => {
        const vinculos = response['content'];

        const resultado = {
          vinculos,
          total: response['totalElements']
        };

        return resultado;
      });
  }

  excluir(codigo: number): Promise<void> {
    return this.http.delete(`${this.vinculosUrl}/${codigo}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(lancamento: Lancamento): Promise<Lancamento> {
    return this.http.post<Lancamento>(this.vinculosUrl, lancamento)
      .toPromise();
  }

  atualizar(lancamento: Lancamento): Promise<Lancamento> {
    return this.http.put<Lancamento>(`${this.vinculosUrl}/${lancamento.codigo}`, lancamento)
      .toPromise()
      .then(response => {
        const vinculoAlterado = response;


        return vinculoAlterado;
      });
  }

  buscarPorCodigo(codigo: number): Promise<Lancamento> {
    return this.http.get<Lancamento>(`${this.vinculosUrl}/${codigo}`)
      .toPromise()
      .then(response => {
        const vinculos = response;


        return vinculos;
      });
  }



}
