import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    private baseURL = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient) {
    }

    getProductsList(categoryId: number | undefined, field: string | undefined, direction: string | undefined, offset: number | undefined, pageSize: number | undefined, latitude: number | undefined, longitude: number | undefined, nearMeDistance: number | undefined): Observable<Product[]> {
        const pramsString = this.generateParamsString(categoryId, field, direction, offset, pageSize, latitude, longitude, nearMeDistance);
        return this.httpClient.get<Product[]>(`${this.baseURL}/products/filter${pramsString}`);
    }

    getProduct(productId: number): Observable<Product> {
        return this.httpClient.get<Product>(`${this.baseURL}/product/${productId}`);
    }

    createProduct(product: Product): Observable<Object> {
        return this.httpClient.post(`${this.baseURL}/product`, product)
    }

    updateProduct(productId: number, product: Product): Observable<Object> {
        return this.httpClient.put(`${this.baseURL}/product/${productId}`, product);
    }

    incrementViews(productId: number): Observable<Product> {
        return this.httpClient.put<Product>(`${this.baseURL}/product/view/${productId}`, {});
    }

    deleteProduct(productId: number): Observable<Object> {
        return this.httpClient.get<any>(`${this.baseURL}/product/${productId}`);
    }

    private generateParamsString(categoryId: number | undefined, field: string | undefined, direction: string | undefined, offset: number | undefined, pageSize: number | undefined, latitude: number | undefined, longitude: number | undefined, nearMeDistance: number | undefined): string {
        let paramsString = '?';
        if (!(typeof categoryId === "undefined")) {
            paramsString += `&categoryId=${categoryId}`;
        }
        if (!(typeof field === "undefined")) {
            paramsString += `&field=${field}`;
        }
        if (!(typeof direction === "undefined")) {
            paramsString += `&direction=${direction}`;
        }
        if (!(typeof offset === "undefined")) {
            paramsString += `&offset=${offset}`;
        }
        if (!(typeof pageSize === "undefined")) {
            paramsString += `&pageSize=${pageSize}`;
        }
        if (!(typeof latitude === "undefined")) {
            paramsString += `&latitude=${latitude}`;
        }
        if (!(typeof longitude === "undefined")) {
            paramsString += `&longitude=${longitude}`;
        }
        if (!(typeof nearMeDistance === "undefined")) {
            paramsString += `&nearMeDistance=${nearMeDistance}`;
        }

        return paramsString.replaceAll('?&', '?');
    }

}
