import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppPagedListComponent } from './app-paged-list.component';

describe('AppPagedListComponent', () => {
  let component: AppPagedListComponent;
  let fixture: ComponentFixture<AppPagedListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AppPagedListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AppPagedListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
