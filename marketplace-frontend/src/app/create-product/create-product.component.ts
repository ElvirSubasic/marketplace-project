import {Component, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import {Router} from "@angular/router";

@Component({
    selector: 'app-create-product',
    templateUrl: './create-product.component.html',
    styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {
    product: Product = new Product();

    constructor(
        private productServices: ProductService,
        private router: Router
    ) {
    }

    ngOnInit(): void {
    }

    onSubmit() {
        this.saveProduct();
    }

    saveProduct() {
        this.productServices.createProduct(this.product).subscribe(data => {
                let createdProduct: Product = data as Product;
                if (createdProduct.productId) {
                    this.goToProductsDetailView(createdProduct.productId);
                }
            },
            error => console.error(error));
    }

    handleUpload(event: any) {
        const file = event.target.files[0];
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = () => {
            let fileContents: string = reader.result as string;
            let base64Mark: string = "base64,";
            let dataStart: number = fileContents.indexOf(base64Mark) + base64Mark.length;
            fileContents = fileContents?.substring(dataStart);
            if (this.product) {
                this.product.image = fileContents;
            }
        };
    }

    goToProductsDetailView(productId: number) {
        this.router.navigate([`/product-details/${productId}`]);
    }
}
