import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CupomFormComponent } from './cupom-form.component';

describe('CupomFormComponent', () => {
  let component: CupomFormComponent;
  let fixture: ComponentFixture<CupomFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CupomFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CupomFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
