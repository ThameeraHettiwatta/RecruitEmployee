import { Component, OnInit } from '@angular/core';
import {Employee} from "../../../employee";
import {EmployeeService} from "../../../employee.service";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-all-employees',
  templateUrl: './all-employees.component.html',
  styleUrls: ['./all-employees.component.css']
})
export class AllEmployeesComponent implements OnInit {

  public employees: Employee[];

  constructor(private employeeService: EmployeeService){}

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
        (response: Employee[]) => {
          this.employees = response;
          console.log(this.employees);
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }

}
