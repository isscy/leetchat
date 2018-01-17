/**
 * add by ff 20180117 用户信息的服务
 */

import { Injectable } from '@angular/core';
// 用户信息
export interface UserInfo{
  id:string;
  username:string;
  token:string;
}
// 登陆信息
export interface LoginInfo{
  success:boolean;
  message:string;
  guidePage:string;
  user?:UserInfo;//非必需属性
}

@Injectable()
export class UserService {

  public currentUserKey:string = "currentUser";
  //public storage:Storage = localStorage;
  public storage:Storage = sessionStorage;

  constructor() {}

  saveUserInfo(userInfoString:string) {
    this.storage.setItem(this.currentUserKey, userInfoString);
  }

  removeUserInfo() {
    this.storage.removeItem(this.currentUserKey);
  }

  getUserInfo():UserInfo|null {
    try{
      let userInfoString:string = this.storage.getItem(this.currentUserKey);
      if (userInfoString) {
        let userObj:UserInfo = JSON.parse(userInfoString);
        return userObj;
      }
      return null;
    } catch (e) {
      return null;
    }
  }

  isLoggedIn():boolean{
    return this.storage.getItem(this.currentUserKey) ? true : false;
  }

  getUserName():string{
    let userObj:UserInfo = this.getUserInfo();
    if (userObj!== null){
      return userObj.username;
    }
    return "no-user";
  }

  getToken():string|null {
    let userObj:UserInfo = this.getUserInfo();
    if (userObj !== null){
      return userObj.token;
    }
    return null;
  }
}
