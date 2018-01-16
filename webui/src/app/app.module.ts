import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';


// 第三方modules

// 本地自建modules
import { AppRoutingModule } from './app-routing.module';


import { AppComponent } from './app.component';


// component
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {PageNotFoundComponent} from './pages/404/page-not-found.component';


@NgModule({
  declarations: [
    AppComponent,

    DashboardComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
