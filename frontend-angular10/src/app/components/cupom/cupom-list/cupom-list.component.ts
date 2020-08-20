import {Component, OnInit} from '@angular/core';
import {Cupom} from '../cupom.model';
import {CupomService} from '../cupom.service';
import {Router} from '@angular/router';
import {SimpleDialogComponent} from '../../template/simple-dialog/simple-dialog.component';
import {MatDialog} from '@angular/material/dialog';

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
    'situacao',
    'actions',
  ];

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
    console.log(cupom);
    this.cupomService.delete(cupom.id).subscribe(() => {
      const index = this.cupons.indexOf(cupom);
      this.cupons.splice(index, 1);
    });
  }

  isExpirado(cupom: Cupom): boolean {
    return cupom.situacao === 'EXPIRADO';
  }
}
