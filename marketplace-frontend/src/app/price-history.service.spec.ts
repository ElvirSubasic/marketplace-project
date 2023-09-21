import { TestBed } from '@angular/core/testing';

import { PriceHistoryService } from './price-history.service';

describe('PriceHistoryService', () => {
  let service: PriceHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PriceHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
