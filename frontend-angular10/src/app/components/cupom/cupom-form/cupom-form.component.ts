import {Component, OnInit} from '@angular/core';
import {Cupom} from '../cupom.model';
import {CupomService} from '../cupom.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-cupom-form',
  templateUrl: './cupom-form.component.html',
  styleUrls: ['./cupom-form.component.scss']
})
export class CupomFormComponent implements OnInit {

  cupom: Cupom = {
    id: null,
    codigo: '',
    descricao: '',
    dataCadastro: null,
    dataExpiracao: null,
    dataUso: null,
    valor: 0,
    situacao: 'ATIVO',
  };

  constructor(
    private cupomService: CupomService,
    private routerActive: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.routerActive.paramMap.subscribe(
      params => {
        const id = +params.get('id');

        if (id > 0) {
          this.searchCupomById(id);
        }
      });
  }

  searchCupomById(id: number): void {
    this.cupomService.readById(id).subscribe(
      result => {
        this.cupom = result;
      }
    );
  }

  save(): void {
    this.cupom.dataCadastro = new Date();

    this.cupomService
      .create(this.cupom)
      .subscribe(() => {
        this.router.navigate(['']);
      });
  }
}
