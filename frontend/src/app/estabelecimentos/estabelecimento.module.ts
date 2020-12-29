import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InputMaskModule } from 'primeng/inputmask';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { TooltipModule } from 'primeng/tooltip';
import { PanelModule } from 'primeng/panel';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';

import { SharedModule } from './../shared/shared.module';
import { EstabelecimentoPesquisaComponent } from './estabelecimento-pesquisa/estabelecimento-pesquisa.component';
import { EstabelecimentoCadastroComponent } from './estabelecimento-cadastro/estabelecimento-cadastro.component';
import { EstabelecimentoRoutingModule } from './estabelecimento-routing.module';
import { PessoaCadastroContatoComponent } from './estabelecimento-cadastro-contato/estabelecimento-cadastro-contato.component';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,

    InputTextModule,
    ButtonModule,
    TableModule,
    TooltipModule,
    InputMaskModule,
    PanelModule,
    DialogModule,
    DropdownModule,

    SharedModule,
    EstabelecimentoRoutingModule
  ],
  declarations: [
    EstabelecimentoCadastroComponent,
    EstabelecimentoPesquisaComponent,
    PessoaCadastroContatoComponent
  ],
  exports: []
})
export class EstabelecimentosModule { }
