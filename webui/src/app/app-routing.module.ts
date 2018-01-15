/*
 add by ff at 2018-01-14 路由模块
 */

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

// 自建组件倒入
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {PageNotFoundComponent} from "./pages/404/page-not-found.component";


const routes : Routes = [

  //TODO 新建home组件 把除了登陆登出都当成他的子组件
  {path:'', redirectTo:'/dashboard', pathMatch:'full'},
  {path:'dashboard', component:DashboardComponent},

  /*
  { path: 'login' , component: LoginComponent },
  { path: 'logout', component: LogoutComponent  },
  */

  { path: '**'    , component: PageNotFoundComponent }


];

@NgModule({

  imports : [RouterModule.forRoot(routes)],
  exports : [RouterModule]

})



export class AppRoutingModule {}
