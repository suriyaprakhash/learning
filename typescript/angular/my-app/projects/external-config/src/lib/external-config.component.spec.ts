import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExternalConfigComponent } from './external-config.component';

describe('ExternalConfigComponent', () => {
  let component: ExternalConfigComponent;
  let fixture: ComponentFixture<ExternalConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExternalConfigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ExternalConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
