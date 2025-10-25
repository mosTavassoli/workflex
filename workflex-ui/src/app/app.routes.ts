import { Routes } from '@angular/router';
import { WorkationsListComponent  } from '@features/workations/workations-list/workations-list';


export const routes: Routes = [
  { path: '', component: WorkationsListComponent  },
  { path: '**', redirectTo: '' }
];
