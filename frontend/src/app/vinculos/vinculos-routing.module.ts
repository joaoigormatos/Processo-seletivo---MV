import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';

import { AuthGuard } from './../seguranca/auth.guard';
import { VinculoCadastroComponent } from './vinculos-cadastro/vinculos-cadastro.component';
import { VinculoPesquisaComponent } from './vinculos-pesquisa/vinculos-pesquisa.component';

const routes: Routes = [
  {
    path: '',
    component: VinculoPesquisaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_VINCULO'] }
  },
  {
    path: 'novo',
    component: VinculoCadastroComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_VINCULO'] }
  },

];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class VinculosRoutingModule { }
