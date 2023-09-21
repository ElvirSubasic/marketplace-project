import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import {ProductsListComponent} from "./products-list/products-list.component";
import {CreateProductComponent} from "./create-product/create-product.component";
import {ProductDetailsComponent} from "./product-details/product-details.component";

const routes: Routes = [
  {path: 'products', component: ProductsListComponent},
  {path: 'create-product', component: CreateProductComponent},
  {path: 'product-details/:id', component: ProductDetailsComponent},
  {path: '', redirectTo: 'products', pathMatch: 'full'}
];
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
