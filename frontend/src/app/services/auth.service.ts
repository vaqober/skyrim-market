import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '@models/user';
import { apiUrl, localStorageUserField } from '../app.const';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(user: User): Observable<User> {
    return this.http.post<User>(`${apiUrl}/login`, user)
      .pipe(
        tap(user => localStorage.setItem(localStorageUserField, JSON.stringify(user)))
      )
  }

  logout() {
    return this.http.get<void>(`${apiUrl}/logout`)
  }
}