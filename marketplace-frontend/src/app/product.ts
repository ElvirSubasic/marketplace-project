import {Category} from "./category";
import {PriceHistory} from "./price-history";

export class Product {
    productId: number | undefined;
    name: string | undefined;
    description: string | undefined;
    price: number | undefined;
    latitude: number | undefined;
    longitude: number | undefined;
    views: number | undefined;
    image: string | undefined;
    category: Category | undefined;
    priceHistories: PriceHistory[] | undefined;
    distance: number | undefined;
}
