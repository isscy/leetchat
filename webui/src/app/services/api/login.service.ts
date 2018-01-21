import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest,  HttpParams } from '@angular/common/http';
import { Headers, Http } from '@angular/http';

import { Observable,Subject } from 'rxjs';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { UserService, LoginInfo} from '../user.service';
import { ApiRequestService } from './api-request.service';

export interface LoginRequestParam{
  username:string;
  password:string;
}

@Injectable()
export class LoginService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  public guidePage:string = "/home/dashboard";
  constructor(
    private router:Router,
    private userService: UserService,
    private apiRequest: ApiRequestService,
    private http: HttpClient

  ) {}


  test(username:string, password:string):Promise<LoginInfo>{
    let bodyData:LoginRequestParam = {
      "username": username,
      "password": password,
    }
    return this.http.post('http://localhost:8888/login', JSON.stringify(bodyData), {headers:this.headers})

      .toPromise().then(response => response as LoginInfo);




  }


  getToken(username:string, password:string): Observable<any> {
    let me = this;

    let bodyData:LoginRequestParam = {
      "username": username,
      "password": password,
    }
    let loginDataSubject:Subject<any> = new Subject<any>(); // Will use this subject to emit data that we want after ajax login attempt
    let loginInfoReturn:LoginInfo; // Object that we want to send back to Login Page

    this.http.post('http://localhost:8888/login', bodyData, {
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    }).subscribe(jsonResp => {
      console.log(jsonResp);
      loginInfoReturn = {
        "success"    : true,
        "message"    : '',
        "guidePage": this.guidePage,
        "user"       : {
          "id"     : 'a',
          "username"      : 'b',
          "token"      : 'c',
        }
      };

      // store username and jwt token in session storage to keep user logged in between page refreshes
      //this.userService.saveUserInfo(JSON.stringify(loginInfoReturn.user));
      loginDataSubject.next(loginInfoReturn);
    });


    /*
    this.apiRequest.post('login', bodyData)
      .subscribe(jsonResp => {
        if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationStatus === "SUCCESS"){
          //Create a success object that we want to send back to login page
          loginInfoReturn = {
            "success"    : true,
            "message"    : jsonResp.operationMessage,
            "guidePage": this.guidePage,
            "user"       : {
              "id"     : jsonResp.item.userId,
              "username"      : jsonResp.item.emailAddress,
              "token"      : jsonResp.item.token,
            }
          };

          // store username and jwt token in session storage to keep user logged in between page refreshes
          this.userService.saveUserInfo(JSON.stringify(loginInfoReturn.user));
        }
        else {
          //Create a faliure object that we want to send back to login page
          loginInfoReturn = {
            "success":false,
            "message":jsonResp.msgDesc,
            "guidePage":"/login"
          };
        }
        loginDataSubject.next(loginInfoReturn);
      });*/

    return loginDataSubject;
  }

  logout(navigatetoLogout=true): void {
    // clear token remove user from local storage to log user out
    this.userService.removeUserInfo();
    if(navigatetoLogout){
      this.router.navigate(["logout"]);
    }
  }
}
