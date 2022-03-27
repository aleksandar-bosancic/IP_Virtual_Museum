import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TourPurchaseComponent } from './tour-purchase.component';

describe('TourPurchaseComponent', () => {
  let component: TourPurchaseComponent;
  let fixture: ComponentFixture<TourPurchaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TourPurchaseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TourPurchaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
