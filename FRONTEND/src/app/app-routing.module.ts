import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreditCardComponent } from './credit-card/credit-card.component';
import { PersonComponent } from './person/person.component';

const routes: Routes = [{ path: 'addperson', component: PersonComponent }, { path: 'addCreditCard', component: CreditCardComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
