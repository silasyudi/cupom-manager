import {Component, OnInit} from '@angular/core';
import {Cupom} from '../cupom.model';
import {CupomService} from '../cupom.service';
import {Router} from '@angular/router';
import {SimpleDialogComponent} from '../../template/simple-dialog/simple-dialog.component';
import {MatDialog} from '@angular/material/dialog';
import {CupomSearchComponent} from '../cupom-search/cupom-search.component';

@Component({
  selector: 'app-cupom-list',
  templateUrl: './cupom-list.component.html',
  styleUrls: ['./cupom-list.component.scss']
})
export class CupomListComponent implements OnInit {

  cupons: Cupom[];
  displayedColumns: string[] = [
    'codigo',
    'descricao',
    'dataCadastro',
    'dataExpiracao',
    'dataUso',
    'valor',
    'situacao',
    'actions',
  ];
  searchs = [];

  constructor(
    private cupomService: CupomService,
    private router: Router,
    private dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.cupomService.read().subscribe(cupons => {
      this.cupons = cupons;
    });
  }

  pesquisaAvancada(): void {
    this.dialog.open(CupomSearchComponent)
      .afterClosed()
      .subscribe(data => {
        this.search(data);
      });
  }

  search(data: { situacao, dataInicio, dataFim }): void {
    if (!this.isDatasValidas(data)) {
      alert('A data inicial deve ser menor que a data final.');
      return;
    }

    this.addSearch(data);

    this.cupomService
      .readAdvanced(data.situacao, data.dataInicio, data.dataFim)
      .subscribe(cupons => {
        this.cupons = cupons;
      });
  }

  isDatasValidas(data: { situacao, dataInicio, dataFim }): boolean {
    return !(
      data.dataInicio != null
      && data.dataFim != null
      && data.dataInicio > data.dataFim
    );
  }

  adicionarCupom(): void {
    this.router.navigate(['cupons/form']);
  }

  editarCupom(cupom: Cupom): void {
    this.router.navigate(['cupons/form/' + cupom.id]);
  }

  deletarCupom(cupom: Cupom): void {
    this.confirmaRemocao(cupom);
  }

  confirmaRemocao(cupom: Cupom): void {
    this.dialog.open(SimpleDialogComponent,
      {data: {texto: 'Confirma a remoção do cupom? Esta ação não poderá ser desfeita!'}})
      .afterClosed()
      .subscribe(result => {
        if (result) {
          this.executaRemocao(cupom);
        }
      });
  }

  executaRemocao(cupom: Cupom): void {
    this.cupomService
      .delete(cupom.id)
      .subscribe(result => {
        this.cupons = this.cupons.filter(item => item !== cupom);
      });
  }

  isExpirado(cupom: Cupom): boolean {
    return cupom.situacao === 'EXPIRADO';
  }

  addSearch(data: { situacao, dataInicio, dataFim }): void {
    this.searchs = [];

    if (data.situacao) {
      this.searchs.push('Situação: ' + data.situacao);
    }

    if (data.dataInicio) {
      this.searchs.push('Data inicial: ' + data.dataInicio.toLocaleDateString());
    }

    if (data.dataFim) {
      this.searchs.push('Data final: ' + data.dataFim.toLocaleDateString());
    }
  }

  removeSearch(): void {
    this.searchs = [];
    this.ngOnInit();
  }
}
