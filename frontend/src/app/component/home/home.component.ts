import { Component, OnInit } from '@angular/core';
import { UserService } from '../../_services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  currencies?: any;

  constructor(private userService: UserService) { }

  onDateChange(event: Event): any {
    const selectedDate = (event.target as HTMLInputElement).value;
    console.log('Selected date:', selectedDate);

    this.userService.getCurrencyByDate(selectedDate.toString()+'T00:00:00.000Z').subscribe(
      data => {
        this.currencies = data;
      },
      err => {
        this.currencies = null; // JSON.parse(err.error).message;
      }
    );
  }

  ngOnInit(): void {
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
