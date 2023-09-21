export class PriceHistory {
    priceHistoryId: number | undefined;
    price: number | undefined;
    timestamp: Date | undefined;
    private product: any | undefined;

    constructor(price: number | undefined, timestamp: Date | undefined, productId: number | undefined) {
        this.price = price;
        this.timestamp = timestamp;
        this.product = {
            productId: productId
        }
    }

}
