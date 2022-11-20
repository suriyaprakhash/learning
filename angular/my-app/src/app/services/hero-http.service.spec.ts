import { TestBed } from '@angular/core/testing';

import { HeroHttpService } from './hero-http.service';

describe('HeroHttpService', () => {
  let service: HeroHttpService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HeroHttpService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
