import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigConsumerComponent } from './config-consumer.component';

describe('ConfigConsumerComponent', () => {
  let component: ConfigConsumerComponent;
  let fixture: ComponentFixture<ConfigConsumerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfigConsumerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigConsumerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
