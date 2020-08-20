import {Component, OnInit} from '@angular/core';
import {Cupom} from '../cupom.model';
import {CupomService} from '../cupom.service';
import {Router} from '@angular/router';

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
    private router: Router
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

  deletarCupom(id: number): void {

  }

  isExpirado(cupom: Cupom): boolean {
    return cupom.situacao === 'EXPIRADO';
  }
}
