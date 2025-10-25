import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkationsListComponent } from './workations-list';

describe('WorkationsListComponent', () => {
  let component: WorkationsListComponent;
  let fixture: ComponentFixture<WorkationsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WorkationsListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WorkationsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
