import { Injectable } from '@angular/core';

import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toastr: ToastrService) { }

  showSuccess(title: string, message: string): void {
    this.toastr.success(message, title, { timeOut: 1000 });
  }

  showError(title: string, message: string): void {
    this.toastr.error(message, title, { timeOut: 1000 });
  }

  showInfo(title: string, message: string): void {
    this.toastr.info(message, title, { timeOut: 1000 });
  }

  showWarning(title: string, message: string): void {
    this.toastr.warning(message, title, { timeOut: 1000 });
  }

}
