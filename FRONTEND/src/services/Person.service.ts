import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Person } from 'src/classes/Person/Person';

@Injectable()
export class PersonService {

    private personURL: string;

    constructor(private http: HttpClient) {
        console.log(HttpHeaders);
        console.log(of);
        var mainHost = 'http://localhost:';
        var port = '8081';
        var mainURL = '/back/Person/';
        this.personURL = mainHost + port + mainURL;
    }

    public getPersons(): Observable<Person[]> {
        var endpoint = 'get-persons';
        return this.http.get<Person[]>(this.personURL + endpoint);
    }

    public addPerson(user: Person) {
        var endpoint = 'add-person';
        return this.http.post<Person>(this.personURL + endpoint, user);
    }

    public devarePerson(id: number) {
        var endpoint = 'devare-person';
        var param = 'id=';
        return this.http.post(this.personURL + endpoint + param + id.toString(), null);
    }
}