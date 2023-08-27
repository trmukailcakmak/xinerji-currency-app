import { Component, OnInit } from '@angular/core';
import { UserService } from '../../_services/user.service';
import {TokenStorageService} from '../../_services/token-storage.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService, private token: TokenStorageService) { }

  ngOnInit(): void {
    const user = this.token.getUser();
    /*this.userService.getUserBoard(user.email).subscribe(
      data => {*/
    this.content = user.name + user.surname;
      /*},
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );*/
  }
}
