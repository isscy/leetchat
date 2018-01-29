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
    let body:LoginRequestParam = {
      "username": username,
      "password": password,
    }
    let loginDataSubject:Subject<any> = new Subject<any>(); //Subject是一个可观察的事件流中的生产者
    let loginInfoReturn:LoginInfo;
    this.apiRequest.post('/login', body)
      .subscribe(jsonResp => {
        if (jsonResp !== undefined && jsonResp !== null && jsonResp.operationStatus === "SUCCESS"){
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
          this.userService.saveUserInfo(JSON.stringify(loginInfoReturn.user));
        }
        else {
          loginInfoReturn = {
            "success":false,
            "message":jsonResp.msgDesc,
            "guidePage":"/login"
          };
        }
        loginDataSubject.next(loginInfoReturn);
      });
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
