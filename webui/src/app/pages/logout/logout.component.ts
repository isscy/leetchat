import { Component } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { UserService, LoginInfo} from '../../services/user.service';

@Component({
  selector   : 's-logout-pg',
  templateUrl: './logout.component.html',
  styleUrls  : [ './logout.scss'],
})

export class LogoutComponent {
  constructor(private userService: UserService){
    this.userService.removeUserInfo();
  }
}
