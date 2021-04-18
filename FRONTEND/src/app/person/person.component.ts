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

  constructor(private route: ActivatedRoute,
    private router: Router,
    private personService: PersonService) {
    this.person = new Person();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    const idDefault = 0;
    this.person.id = idDefault;
    this.personService.addPerson(this.person);
  }
}
