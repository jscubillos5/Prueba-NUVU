import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, pipe } from 'rxjs';
import { Person } from 'src/classes/Person/Person';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable()
export class PersonService {

    private personURL: string;

    httpOptions = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    }

    constructor(private http: HttpClient) {
        console.log(HttpHeaders);
        console.log(Observable);
        console.log(pipe);
        var mainHost = 'http://localhost:';
        var port = '8081';
        var mainURL = '/Person/';
        this.personURL = mainHost + port + mainURL;
    }

    public getPersons(): Observable<Person[]> {
        var endpoint = 'get-persons';
        return this.http.get<Person[]>(this.personURL + endpoint);
    }

    public addPerson(person: Person) {
        var endpoint = 'add-person';
        var url = this.personURL + endpoint;
        console.log("addPerson url: " + url + " person: " + JSON.stringify(person));
        return this.http.post<Person>(url, JSON.stringify(person), this.httpOptions)
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    public deletePerson(id: number) {
        var endpoint = 'delete-person';
        var param = '?id=';
        var url = this.personURL + endpoint + param + + id.toString();
        console.log("deletePerson url: " + url);
        return this.http.post<Person>(url, null, this.httpOptions)
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    private handleError(error: HttpErrorResponse): any {
        if (error.status == 406) {
            console.error(error.message);
            alert("The person has already been added or removed, please review");
        } else if (error.status == 409) {
            console.error(error.message);
            alert("Server error, please contact customer service");
        }
        return throwError(
            'Something bad happened; please try again later.');
    }
}