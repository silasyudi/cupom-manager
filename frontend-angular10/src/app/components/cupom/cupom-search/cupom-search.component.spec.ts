import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CupomSearchComponent } from './cupom-search.component';

describe('CupomSearchComponent', () => {
  let component: CupomSearchComponent;
  let fixture: ComponentFixture<CupomSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CupomSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CupomSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
