import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpParamsOptions} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";
import {PriceHistory} from "./price-history";
import {Category} from "./category";

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    private baseURL = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient) {
    }

    getCategoryList(field: string | undefined, direction: string | undefined, offset: number | undefined, pageSize: number | undefined): Observable<Category[]> {
        const pramsString = this.generateParamsString(field, direction, offset, pageSize);
        return this.httpClient.get<Category[]>(`${this.baseURL}/categories/sort-pagination${pramsString}`);
    }

    getCategory(categoryId: number): Observable<Category> {
        return this.httpClient.get<Category>(`${this.baseURL}/category/${categoryId}`);
    }

    createCategory(category: Category): Observable<Object> {
        return this.httpClient.post(`${this.baseURL}/category`, category);
    }

    updateCategory(categoryId: number, category: Category): Observable<Object> {
        return this.httpClient.put(`${this.baseURL}/category/${categoryId}`, category);
    }

    deleteCategory(categoryId: number): Observable<Object> {
        return this.httpClient.get<any>(`${this.baseURL}/category/${categoryId}`);
    }

    private generateParamsString(field: string | undefined, direction: string | undefined, offset: number | undefined, pageSize: number | undefined): string {
        let paramsString = '?';
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

        return paramsString.replaceAll('?&', '?');
    }
}
