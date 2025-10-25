export interface Workation {
  id: string;
  employee: string;
  originCountry: string;
  destinationCountry: string;
  startDate: string;
  endDate: string;
  workingDays: number;
  riskLevel: 'HIGH' | 'LOW' | 'NO';
}
