import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ProductService} from "../product.service";
import {Product} from "../product";
import {PriceHistoryService} from "../price-history.service";
import {PriceHistory} from "../price-history";

@Component({
    selector: 'app-product-details',
    templateUrl: './product-details.component.html',
    styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
    product: Product | undefined;
    temporaryProduct: Product | undefined;
    productId: number | any;
    showEditButton: boolean = true;
    showSaveButton: boolean = false;
    showCancelButton: boolean = false;
    showViewForm: boolean = true;
    showEditForm: boolean = false;

    constructor(private activatedRoute: ActivatedRoute, private productService: ProductService, private priceHistoryService: PriceHistoryService) {
    }

    ngOnInit(): void {
        this.productId = this.activatedRoute.snapshot.paramMap.get('id');
        this.getProduct();
        this.incrementViews();
    }

    private getProduct() {
        this.productService.getProduct(
            this.productId
        ).subscribe(data => {
            data.image = data.image?.includes("data:image/png;base64,") ? data.image : `data:image/png;base64, ${data.image}`;
            this.product = data;
        })
    }

    private saveProduct() {
        console.log('SAVE PROD :: ' + JSON.stringify(this.temporaryProduct))
        if (this.temporaryProduct) {

            this.productService.updateProduct(this.productId, this.temporaryProduct)
                .subscribe(() => {
                    this.getProduct();
                });
        }
    }

    private savePriceHistory() {
        this.priceHistoryService.createPriceHistory(
            new PriceHistory(this.temporaryProduct?.price, new Date(), this.productId)
        );
    }

    private incrementViews() {
        this.productService.incrementViews(this.productId).subscribe();
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
            if (this.temporaryProduct) {
                this.temporaryProduct.image = fileContents;
            }
        };
    }

    handleChange(event: any) {
        // @ts-ignore
        this.temporaryProduct[event.target.getAttribute('name')] = event.target.value;
    }

    handleEditClick(event: any) {
        console.log('Start')
        this.temporaryProduct = JSON.parse(JSON.stringify(this.product));
        this.showEditForm = true;
        this.showViewForm = false;
        this.showEditButton = false;
        this.showSaveButton = true;
        this.showCancelButton = true;
        console.log('Finish')
    }

    handleCancelClick(event: any) {
        this.showEditForm = false;
        this.showViewForm = true;
        this.showEditButton = true;
        this.showCancelButton = false;
        this.showSaveButton = false;
    }

    handleSaveClick(event: any) {
        if (this.temporaryProduct?.price !== this.product?.price) {
            this.savePriceHistory();
        }
        this.saveProduct();
        this.showEditForm = false;
        this.showViewForm = true;
        this.showEditButton = true;
        this.showCancelButton = false;
        this.showSaveButton = false;
    }

}
