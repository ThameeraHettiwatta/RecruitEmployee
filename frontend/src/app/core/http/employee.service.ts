import { Injectable } from '@angular/core';
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Employee } from "../../modules/employee/employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employees`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/employees`, employee);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    // console.log(employee);
    return this.http.put<Employee>(`${this.apiServerUrl}/employees`, employee);
  }

  public deleteEmployee(empId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/employees/${empId}`);
  }

  public getEmployeeById(empId: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiServerUrl}/employees/${empId}`);
  }

  public getEmployeeByProjectId(projectId: number): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employees/${projectId}/employees`);
  }
}
