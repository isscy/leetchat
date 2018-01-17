/*
 add by ff at 2018-01-14 路由模块
 */

import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

// 自建组件倒入
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {PageNotFoundComponent} from "./pages/404/page-not-found.component";
import {HomeComponent} from "./pages/home/home.component";
import {LoginComponent} from "./pages/login/login.component";
import {LogoutComponent} from "./pages/logout/logout.component";


const routes : Routes = [

  //TODO 新建home组件 把除了登陆登出都当成他的子组件


  {
    path: 'home',
    component: HomeComponent,
    //canActivate:[AuthGuard],
    children:[
      { path: '', redirectTo: '/home/dashboard/order', pathMatch: 'full', data:[{selectedHeaderItemIndex:1, selectedSubNavItemIndex:-1}] },  // Default path (if no deep path is specified for home component like webui/home then it will by default show ProductsComponent )
      { path: 'dashboard', component: DashboardComponent, data: [{selectedHeaderItemIndex:0, selectedSubNavItemIndex:-1}],

      }/*,
      { path:'setting'    , component: SettingComponent, data:[{selectedHeaderItemIndex:1, selectedSubNavItemIndex:-1}]  },*/
    ]
  },


  {path:'dashboard', component:DashboardComponent},


  { path: 'login' , component: LoginComponent },
  { path: 'logout', component: LogoutComponent  },

  {path:'', redirectTo:'/dashboard', pathMatch:'full'},
  { path: '**'    , component: PageNotFoundComponent }


];

@NgModule({

  imports : [RouterModule.forRoot(routes)],
  exports : [RouterModule]

})



export class AppRoutingModule {}
