import { Component, OnInit } from '@angular/core';
import { providerType } from '../interface/providerType';
import { UserService } from '../service/user.service';
import swal from 'sweetalert';

@Component({
  selector: 'app-providers-page',
  templateUrl: './providers-page.component.html',
  styleUrls: ['./providers-page.component.css'],
})
export class ProvidersPageComponent implements OnInit {
  providers: providerType[] = [];

  constructor(private service: UserService) {}

  ngOnInit() {
    this.service.getAllProviders().subscribe((res: providerType[]) => {
      this.providers = res;
    });
  }

  showUsersList(provider: providerType): void {
    const name: String = provider.providerName;
    const list: Array<String> = provider.usersList;
    // should redirect to a new page
    swal("" + name, "" + list);
  }
}
