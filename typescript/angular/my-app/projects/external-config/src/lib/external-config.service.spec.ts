import { TestBed } from '@angular/core/testing';

import { ExternalConfigService } from './external-config.service';

describe('ExternalConfigService', () => {
  let service: ExternalConfigService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExternalConfigService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
