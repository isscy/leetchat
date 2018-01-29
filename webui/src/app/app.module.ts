import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { NgZorroAntdModule } from 'ng-zorro-antd';


// 第三方modules

// 本地自建modules
import { AppRoutingModule } from './app-routing.module';


import { AppComponent } from './app.component';


// component
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {PageNotFoundComponent} from './pages/404/page-not-found.component';
import {HomeComponent} from './pages/home/home.component';
import {LoginComponent} from './pages/login/login.component';
import {LogoutComponent} from './pages/logout/logout.component';

// Services
import {AppConfig} from './app-config';
import {UserService} from './services/user.service';
import {LoginService} from './services/api/login.service';
import {ApiRequestService} from './services/api/api-request.service';


@NgModule({
  declarations: [
    AppComponent,

    DashboardComponent,
    PageNotFoundComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgZorroAntdModule.forRoot()
  ],
  providers: [
    AppConfig,
    UserService,
    LoginService,
    ApiRequestService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
