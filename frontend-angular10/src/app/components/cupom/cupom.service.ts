import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {EMPTY, Observable} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {Cupom} from './cupom.model';

@Injectable({
  providedIn: 'root',
})
export class CupomService {
  baseUrl = '/api/cupons';

  constructor(private http: HttpClient) {
  }

  create(cupom: Cupom): Observable<Cupom> {
    return this.http.post<Cupom>(this.baseUrl, cupom).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  readById(cupom: number): Observable<Cupom> {
    const url = `${this.baseUrl}/${cupom}`;
    return this.http.get<Cupom>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  read(): Observable<Cupom[]> {
    return this.http.get<Cupom[]>(this.baseUrl).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  update(cupom: Cupom): Observable<Cupom> {
    return this.http.put<Cupom>(this.baseUrl, cupom).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  delete(id: number): Observable<Cupom> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<Cupom>(url).pipe(
      map((obj) => obj),
      catchError((e) => this.errorHandler(e))
    );
  }

  errorHandler(e: any): Observable<any> {
    console.log(e);
    const message = e?.error?.exception ?? 'Ocorreu um erro inexperado.';
    alert(message);
    return EMPTY;
  }
}
