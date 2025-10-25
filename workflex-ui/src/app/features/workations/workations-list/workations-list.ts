import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatSort, MatSortModule} from '@angular/material/sort';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {WorkationService} from '../../../core/services/workation.service';
import {Workation} from '../../../core/models/workation.model';

@Component({
  selector: 'app-workations-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatSortModule, MatProgressSpinnerModule],
  templateUrl: './workations-list.html',
  styleUrl: './workations-list.scss'
})
export class WorkationsListComponent implements OnInit, AfterViewInit {
  displayedColumns = ['employee', 'originCountry', 'destinationCountry', 'startDate', 'endDate', 'workingDays', 'riskLevel'];
  dataSource = new MatTableDataSource<Workation>([]);
  loading = true;

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private workationService: WorkationService) {
  }

  ngOnInit(): void {
    this.dataSource.sortingDataAccessor = (item, property) => {
      if (['startDate', 'endDate'].includes(property)) {
        const [d, m, y] = (item as any)[property].split('/').map((n: string) => +n);
        return new Date(y, m - 1, d).getTime();
      }
      return (item as any)[property];
    };

    this.workationService.getAll().subscribe({
      next: data => {
        this.dataSource.data = data;
        this.loading = false;
      },
      error: () => (this.loading = false)
    });
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
  }

  flagUrl(country: string): string {
    const map: Record<string, string> = {
      'germany': 'de', 'united states': 'us', 'ukraine': 'ua',
      'belgium': 'be', 'spain': 'es', 'greece': 'gr', 'india': 'in'
    };
    return `https://flagcdn.com/24x18/${map[country.trim().toLowerCase()] ?? 'xx'}.png`;
  }

  riskLabel(level: string): string {
    return level === 'HIGH' ? 'High risk' : 'No risk';
  }

  riskClass(level: string): string {
    return level === 'HIGH' ? 'high' : level === 'LOW' ? 'low' : 'no';
  }

  riskIcon(level: string): string {
    switch (level) {
      case 'HIGH':
        return '/assets/icons/red-risk.svg';
      case 'LOW':
        return '/assets/icons/yellow-risk.svg';
      case 'NO':
        return '/assets/icons/green-risk.svg';
      default:
        return '';
    }
  }

}


// import {AfterViewInit, Component, OnInit, ViewChild} from '@angular/core';
// import {CommonModule} from '@angular/common';
// import {MatTableModule, MatTableDataSource} from '@angular/material/table';
// import {MatSort, MatSortModule} from '@angular/material/sort';
// import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
// import {Workation} from '../../../core/models/workation.model';
// import {WorkationService} from '../../../core/services/workation.service';
//
//
// @Component({
//   selector: 'app-workations-list',
//   imports: [CommonModule, MatTableModule, MatSortModule, MatProgressSpinnerModule],
//   templateUrl: './workations-list.html',
//   styleUrl: './workations-list.scss'
// })
// export class WorkationsListComponent implements OnInit, AfterViewInit {
//   displayedColumns = [
//     'employee',
//     'originCountry',
//     'destinationCountry',
//     'startDate',
//     'endDate',
//     'workingDays',
//     'riskLevel'
//   ];
//
//   dataSource = new MatTableDataSource<Workation>([]);
//   @ViewChild(MatSort, { static: false }) sort!: MatSort;
//
//   constructor(private workationService: WorkationService) {
//   }
//
//   loading = true;
//
//   ngOnInit(): void {
//     this.workationService.getAll().subscribe({
//       next: data => {
//         console.log('Data received:', data);
//         this.dataSource.data = data;
//         // this.dataSource.sort = this.sort;
//         this.loading = false;
//
//         if (this.sort) {
//           this.dataSource.sort = this.sort;
//         }
//       },
//       error: () => (this.loading = false)
//     });
//   }
//
//   ngAfterViewInit(): void {
//     this.dataSource.sort = this.sort;
//   }
//
//   flagUrl(country: string): string {
//     if (!country) return '';
//     const map: Record<string, string> = {
//       'germany': 'de',
//       'united states': 'us',
//       'ukraine': 'ua',
//       'belgium': 'be',
//       'spain': 'es',
//       'greece': 'gr',
//       'india': 'in'
//     };
//     const key = country.trim().toLowerCase();
//     const code = map[key] ?? 'xx';
//     return `https://flagcdn.com/24x18/${code}.png`;
//   }
//
//   riskLabel(level: string): string {
//     return level === 'HIGH' ? 'High risk' : 'No risk';
//   }
//
//   riskClass(level: string): string {
//     switch (level) {
//       case 'HIGH':
//         return 'high';
//       case 'LOW':
//         return 'low';
//       case 'NO':
//         return 'no';
//       default:
//         return '';
//     }
//   }
// }
