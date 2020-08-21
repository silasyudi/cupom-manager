import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-cupom-search',
  templateUrl: './cupom-search.component.html',
  styleUrls: ['./cupom-search.component.scss']
})
export class CupomSearchComponent implements OnInit {

  data = {
    situacao: '',
    dataInicio: null,
    dataFim: null,
  };

  constructor() {
  }

  ngOnInit(): void {
  }
}
