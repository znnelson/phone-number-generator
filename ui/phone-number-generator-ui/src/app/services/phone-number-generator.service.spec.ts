import { TestBed } from '@angular/core/testing';

import { PhoneNumberGeneratorService } from './phone-number-generator.service';

describe('PhoneNumberGeneratorService', () => {
  let service: PhoneNumberGeneratorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PhoneNumberGeneratorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
