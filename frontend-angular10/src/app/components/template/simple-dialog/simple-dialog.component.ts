import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-simple-dialog',
  templateUrl: './simple-dialog.component.html',
  styleUrls: ['./simple-dialog.component.scss']
})
export class SimpleDialogComponent implements OnInit {

  texto = '';

  constructor(@Inject(MAT_DIALOG_DATA) public data) {
    if (data != null) {
      this.texto = data.texto;
    } else {
      this.texto = 'Você confirma a ação?';
    }
  }

  ngOnInit(): void {
  }

}
