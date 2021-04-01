import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import {Employee} from "./employee";
import {EmployeeService} from "../../core/http/employee.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-all-employees',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  public employees: Employee[];

  constructor(private employeeService: EmployeeService){}

    displayedColumns: string[] = ['empId', 'empName', 'empSalary', 'projectId', 'empEmail'];
    dataSource = new MatTableDataSource<Employee>();

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
        (response: Employee[]) => {
          // this.employees = response;
          // console.log(this.employees);
          this.dataSource.data = response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
    );
  }

    @ViewChild(MatPaginator) paginator: MatPaginator;

    ngAfterViewInit() {
        this.dataSource.paginator = this.paginator;
    }

}
