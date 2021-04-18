import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Person } from 'src/classes/Person/Person';
import { PersonService } from 'src/services/Person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  person: Person;
  persons: Person[];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private personService: PersonService) {
    this.person = new Person();
    this.persons = [];
  }

  ngOnInit(): void {
    this.personService.getPersons().subscribe(data => {
      this.persons = data;
    });
  }

  onSubmit() {
    var lastId = 0;
    this.personService.getPersons().subscribe(data => {
      data.forEach(listPersons => {
        lastId = lastId + 1;
      });
    });
    if (lastId != undefined) {
      this.person.id = lastId;
    }
    this.personService.addPerson(this.person);
  }
}
