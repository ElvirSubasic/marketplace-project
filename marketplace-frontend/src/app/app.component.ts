import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Marketplace';
  productListNavItem = "Product List";
  createProductNavItem= "Add Product"
  authorRights = "All Rights Reserved 2023 @ElvirSubasic";
}
