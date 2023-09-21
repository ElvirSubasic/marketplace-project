import {Component, numberAttribute, OnInit} from '@angular/core';
import {Product} from "../product";
import {ProductService} from "../product.service";
import haversine from 'haversine-distance';
import {Router} from "@angular/router";
import {CategoryService} from "../category.service";
import {Category} from "../category";

@Component({
    selector: 'app-products-list',
    templateUrl: './products-list.component.html',
    styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit {
    products: Product[] | undefined;
    categories: Category[] | undefined;
    categoryId: number | undefined;
    field: string | undefined;
    direction: string | undefined;
    offset: number | undefined;
    pageSize: number | undefined;
    latitude: number | undefined;
    longitude: number | undefined;
    nearMeDistance: number | undefined;

    constructor(private productService: ProductService, private categoryService: CategoryService, private router: Router) {
    }

    ngOnInit(): void {
        this.offset = 0;
        this.pageSize = 10;
        this.nearMeDistance = 10;
        this.getLocation();
        this.getCategories();
        this.getProducts();
    }

    getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition((position) => {
                if (position) {
                    this.latitude = position.coords.latitude;
                    this.longitude = position.coords.longitude;
                }
            });
        } else {
            alert("Geolocation is not supported by this browser.");
        }
    }

    private getProducts() {
        this.productService.getProductsList(
            this.categoryId,
            this.field,
            this.direction,
            this.offset,
            this.pageSize,
            this.latitude,
            this.longitude,
            this.nearMeDistance
        ).subscribe(data => {
            data.forEach(item => {
                item.image = item.image?.includes("data:image/png;base64,") ? item.image : `data:image/png;base64, ${item.image}`;
                if (item.latitude && item.longitude && this.latitude && this.longitude) {
                    item.distance = Number(
                        (haversine({lat: this.latitude, lng: this.longitude}, {lat: item.latitude, lng: item.longitude}) / 1000).toFixed(2)
                    );
                }
            });
            this.products = data;
        })
    }

    getCategories() {
        this.categoryService.getCategoryList('name', 'ASC', undefined, undefined).subscribe(data => {
            this.categories = data;
        });
    }

    handleCategorySelect(event: any) {
        this.categoryId = event.target.getAttribute("data-categoryId");
        this.getProducts();
    }

    goToProductsDetailView(event: any) {
        this.router.navigate([`/product-details/${event.target.getAttribute('data-productId')}`]);
    }

    handleNearMeChange(event: any) {
        this.nearMeDistance = event.target.value;
        this.getProducts();
    }

    handleSort(event: any) {
        this.field = event.target.getAttribute("data-name");
        this.direction = this.direction && this.direction === 'ASC'
            ? 'DES'
            : 'ASC';
        this.getProducts();
    }

    handlePageSizeChange(event: any) {
        this.pageSize = event.target.value;
    }

    handlePreviousPageClick(event: any) {
        if (typeof this.offset != "undefined") {
            this.offset = this.offset > 0 ? this.offset - 1 : 0;
            this.getProducts();
        }
    }

    handleNextPageClick(event: any) {
        if (typeof this.offset != "undefined") {
            this.offset += 1;
            this.getProducts();
        }
    }
}
