import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { AuthGuard } from './../seguranca/auth.guard';
import { EstabelecimentoCadastroComponent } from './estabelecimento-cadastro/estabelecimento-cadastro.component';
import { EstabelecimentoPesquisaComponent } from './estabelecimento-pesquisa/estabelecimento-pesquisa.component';

const routes: Routes = [
  {
    path: '',
    component: EstabelecimentoPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_ESTABELECIMENTO'] }
  },
  {
    path: 'nova',
    component: EstabelecimentoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_ESTABELECIMENTO'] }
  },
  {
    path: ':codigo',
    component: EstabelecimentoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_ESTABELECIMENTO'] }
  }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class EstabelecimentoRoutingModule { }
