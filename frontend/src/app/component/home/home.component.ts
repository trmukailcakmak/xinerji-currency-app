import { Component, OnInit } from '@angular/core';
import { UserService } from '../../_services/user.service';
import {NotificationService} from '../../_helpers/notification.service';
import {AppComponent} from '../../app.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currencies?: any;
  isLoggedIn?: any;

  constructor(private userService: UserService, private notifyService: NotificationService, private appComponent: AppComponent) {
    this.isLoggedIn = appComponent.isLoggedIn;
  }
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
  onDateChange(event: Event): any {
    const selectedDate = (event.target as HTMLInputElement).value;
    console.log('Selected date:', selectedDate);
    if (this.isLoggedIn) {
      this.userService.getCurrencyByDate(selectedDate.toString()+'T00:00:00.000Z').subscribe(
        data => {
          this.currencies = data;
        },
        err => {
          this.currencies = null;
          console.log(err.error.message);
          this.showToasterWarning('Warn', 'Cannot found any cuurency rate for this date');
        }
      );
    } else {
      this.showToasterError('Error', 'Please login');
    }

  }

  ngOnInit(): void {
    if (this.isLoggedIn) {
      this.userService.getCurrencyByDate('2023-08-25T00:00:00.000Z').subscribe(
        data => {
          this.currencies = data;
        },
        err => {
          this.currencies = JSON.parse(err.error).message;
        }
      );
    }
  }
}
