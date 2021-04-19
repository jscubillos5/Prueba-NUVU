import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PersonService } from 'src/services/Person.service';
import { Person } from 'src/classes/Person/Person';


@Component({
  selector: 'app-person-form',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent {

  person: Person;
  persons: Person[];

  constructor(private route: ActivatedRoute,
    private router: Router,
    private personService: PersonService) {
    this.person = new Person();
    this.persons = [];
  }

  ngOnInit() {
    this.refreshPerson()
  }

  refreshPerson() {
    this.personService.getPersons()
      .subscribe(data => {
        console.log(data)
        this.persons = data;
      })

  }

  clearformPerson() {
    this.person = new Person();
  }

  addPerson(): void {
    const idDefault = 0;
    this.person.id = idDefault;
    this.personService.addPerson(this.person).subscribe(data => {
      console.log(data)
      this.refreshPerson();
    });
    this.clearformPerson();
  }

  deletePerson(id: number, name: string): void {
    if (confirm("Are you sure to delete: " + name)) {
      this.personService.deletePerson(id).subscribe(data => {
        console.log(data)
        this.refreshPerson();
      });
    }
  }
}
