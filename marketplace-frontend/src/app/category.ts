import {Product} from "./product";

export class Category {
    categoryId: number | undefined;
    name: string | undefined;
    products: Product[] | undefined;
}
