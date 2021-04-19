import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, pipe } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { CreditCard } from 'src/classes/CreditCard/CreditCard';

@Injectable()
export class CreditCardService {

    private creditCardURL: string;

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
        var mainURL = '/CreditCard/';
        this.creditCardURL = mainHost + port + mainURL;
    }

    public getCreditCardByPerson(idPerson: number): Observable<CreditCard[]> {
        var endpoint = 'get-credit-cards';
        var param = '?idPerson=';
        var url = this.creditCardURL + endpoint + param + + idPerson.toString();
        console.log("getCreditCardByPerson url: " + url);        
        return this.http.get<CreditCard[]>(url);
    }

    public addcreditCard(creditCard: CreditCard) {
        var endpoint = 'add-credit-card';
        var url = this.creditCardURL + endpoint;
        console.log("addcreditCard url: " + url + " creditCard: " + JSON.stringify(creditCard));
        return this.http.post<CreditCard>(url, JSON.stringify(creditCard), this.httpOptions)
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    public deletecreditCard(id: number) {
        var endpoint = 'delete-credit-card';
        var param = '?id=';
        var url = this.creditCardURL + endpoint + param + + id.toString();
        console.log("deletecreditCard url: " + url);
        return this.http.post<CreditCard>(url, null, this.httpOptions)
            .pipe(
                retry(1),
                catchError(this.handleError)
            )
    }

    private handleError(error: HttpErrorResponse): any {
        if (error.status == 406) {
            console.error(error.message);
            alert("The credit card has already been added or removed, please review");
        } else if (error.status == 409) {
            console.error(error.message);
            alert("Server error, please contact customer service");
        }
        return throwError(
            'Something bad happened; please try again later.');
    }
}