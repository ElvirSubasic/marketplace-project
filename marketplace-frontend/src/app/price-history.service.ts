import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpParamsOptions} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "./product";
import {PriceHistory} from "./price-history";
import {Category} from "./category";

@Injectable({
    providedIn: 'root'
})
export class PriceHistoryService {
    private baseURL = "http://localhost:8080/api/v1";

    constructor(private httpClient: HttpClient) {
    }

    getPriceHistoryList(field: string, direction: string, offset: number, pageSize: number): Observable<PriceHistory[]> {
        const pramsString = this.generateParamsString(field, direction, offset, pageSize);
        return this.httpClient.get<PriceHistory[]>(`${this.baseURL}/priceHistories/sort-pagination${pramsString}`);
    }

    getPriceHistory(priceHistoryId: number): Observable<PriceHistory> {
        return this.httpClient.get<PriceHistory>(`${this.baseURL}/priceHistory/${priceHistoryId}`);
    }

    createPriceHistory(priceHistory: PriceHistory): Observable<Object> {
        return this.httpClient.post(`${this.baseURL}/priceHistory`, priceHistory);
    }

    updatePriceHistory(priceHistoryId: number, priceHistory: PriceHistory): Observable<Object> {
        return this.httpClient.put(`${this.baseURL}/priceHistory/${priceHistoryId}`, priceHistory);
    }

    deletePriceHistory(priceHistoryId: number): Observable<Object> {
        return this.httpClient.get<any>(`${this.baseURL}/priceHistory/${priceHistoryId}`);
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
