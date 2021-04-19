import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CreditCard } from 'src/classes/CreditCard/CreditCard';
import { Person } from 'src/classes/Person/Person';
import { CreditCardService } from 'src/services/CreditCard.service';
import { PersonService } from 'src/services/Person.service';

@Component({
  selector: 'app-credit-card',
  templateUrl: './credit-card.component.html',
  styleUrls: ['./credit-card.component.css']
})
export class CreditCardComponent implements OnInit {

  creditCard: CreditCard;
  persons: Person[];
  creditCards: CreditCard[];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private creditCardService: CreditCardService,
    private personService: PersonService) {
    this.creditCard = new CreditCard();
    this.persons = [];
    this.creditCards = [];
  }

  ngOnInit() {
    this.refreshCreditCard()
  }

  refreshCreditCard() {
    this.creditCards = [];
    this.personService.getPersons().subscribe(data => {
      console.log(data)
      this.persons = data;
      this.persons.forEach((value) => {
        console.log("value.id: " + value.id + " value.identification: " + value.identification)
        this.loadCreditCards(value.id);
      });
    });
  }

  loadCreditCards(idPerson: number) {
    this.creditCardService.getCreditCardByPerson(idPerson)
      .subscribe(data => {
        console.log(data)
        if (data.length > 0) {
          data.forEach((value) => {
            this.creditCards.push(value);
          });
        }
      });
  }

  clearformcreditCard() {
    this.creditCard = new CreditCard();
  }

  addCreditCard(): void {
    const idDefault = 0;
    const maxValuenumber = 9999999999;
    const maxValueCVC = 999;
    this.creditCard.id = idDefault;
    if (this.creditCard.number > maxValuenumber) {
      alert("The number of a credit card cannot be greater than 10 numbers");
    }
    else if (this.creditCard.cvc > maxValueCVC) {
      alert("The CVC of a credit card cannot be greater than 3 numbers");
    }
    else {
      this.creditCardService.addcreditCard(this.creditCard).subscribe(data => {
        console.log(data)
        this.refreshCreditCard();
      });
      this.clearformcreditCard();
    }
  }

  deleteCreditCard(id: number, number: number): void {
    if (confirm("Are you sure to delete: " + number)) {
      this.creditCardService.deletecreditCard(id).subscribe(data => {
        console.log(data)
        this.refreshCreditCard();
      });
    }
  }

}
