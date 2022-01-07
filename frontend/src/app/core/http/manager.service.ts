import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Employee } from 'src/app/modules/employee/employee';
import { Manager } from 'src/app/modules/manager/manager';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ManagerService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getManagers(): Observable<Manager[]> {
    return this.http.get<Manager[]>(`${this.apiServerUrl}/managers/getAllEmployee`);
  }

  public addManager(manager: Manager): Observable<Manager> {
    console.log(manager);
    return this.http.post<Manager>(`${this.apiServerUrl}/managers/addEmployee`, manager);
  }

  public updateManager(manager: Manager): Observable<Manager> {
    // console.log(manager);
    return this.http.put<Manager>(`${this.apiServerUrl}/managers/updateEmployee`, manager);
  }

  public deleteManager(managerId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/managers/deleteEmployee/${managerId}`);
  }

  public getEmployeesByManagerId(managerId: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiServerUrl}/managers/getEmployees/${managerId}`);
  }

  public getManagerById(managerId: number): Observable<Manager> {
    return this.http.get<Manager>(`${this.apiServerUrl}/managers/getEmployee/${managerId}`);
  }
}
