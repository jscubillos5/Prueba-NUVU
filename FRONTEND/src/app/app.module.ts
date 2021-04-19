import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PersonComponent } from './person/person.component';
import { PersonService } from 'src/services/Person.service';
import { CreditCardComponent } from './credit-card/credit-card.component';
import { CreditCardService } from 'src/services/CreditCard.service';

@NgModule({
  declarations: [
    AppComponent,
    PersonComponent,
    CreditCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [PersonService, CreditCardService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
