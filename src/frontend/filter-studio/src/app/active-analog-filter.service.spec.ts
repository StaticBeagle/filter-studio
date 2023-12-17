import { TestBed } from '@angular/core/testing';

import { ActiveAnalogFilterService } from './active-analog-filter.service';

describe('ActiveAnalogFilterService', () => {
  let service: ActiveAnalogFilterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ActiveAnalogFilterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
