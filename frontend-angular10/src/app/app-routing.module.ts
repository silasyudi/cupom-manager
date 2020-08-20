import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CupomListComponent} from './components/cupom/cupom-list/cupom-list.component';
import {CupomFormComponent} from './components/cupom/cupom-form/cupom-form.component';

const routes: Routes = [
  {
    path: '',
    component: CupomListComponent
  },
  {
    path: 'cupons/form',
    component: CupomFormComponent
  },
  {
    path: 'cupons/form/:id',
    component: CupomFormComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
