import { Component } from '@angular/core';
import { UserService } from 'src/app/service/user.service';

@Component({
    selector: 'app-user-smart-meter',
    templateUrl: './user-smart-meter.component.html',
    styleUrls: ['./user-smart-meter.component.css']
})

export class UserSmartMeterComponent {
    constructor(private service: UserService) {}

    ngOnInit() {
        this.service.getUserByName("logged in username").subscribe((res) => {
            console.log(res);
        })
    }
}