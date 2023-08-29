import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../_services/auth.service';
import { TokenStorageService } from '../../_services/token-storage.service';
import {UserService} from "../../_services/user.service";
import {NotificationService} from "../../_helpers/notification.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private userService: UserService, private tokenStorage: TokenStorageService, private notifyService: NotificationService) { }
  showToasterSuccess(title: string, msg: string): any {
    this.notifyService.showSuccess(msg, title);
  }

  showToasterError(title: string, msg: string): any {
    this.notifyService.showError(msg, title);
  }

  showToasterInfo(title: string, msg: string): any {
    this.notifyService.showInfo(msg, title);
  }

  showToasterWarning(title: string, msg: string): any {
    this.notifyService.showWarning(msg, title);
  }
  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.userService.getUserBoard(data.email).subscribe(user => {
            this.tokenStorage.saveUser(user);
            this.isLoginFailed = false;
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().roles;
            this.reloadPage();
          },
          err => {
            this.errorMessage = err.error.message;
          });
      },
      err => {
        this.errorMessage = err.error.message;
        this.showToasterWarning('Error', 'Login Failed');
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
  }
}
