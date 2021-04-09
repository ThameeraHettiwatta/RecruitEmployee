import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/modules/employee/employee';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/managers/getAllEmployee`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/managers/addEmployee`, employee);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    // console.log(employee);
    return this.http.put<Employee>(`${this.apiServerUrl}/managers/updateEmployee`, employee);
  }

  public deleteEmployee(managerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/managers/deleteEmployee/${managerId}`);
  }

  public getEmployeeById(managerId: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiServerUrl}/managers/getAllEmployees/${managerId}`);
  }
}
