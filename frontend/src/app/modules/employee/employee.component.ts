import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { Employee } from "./employee";
import { EmployeeService } from "../../core/http/employee.service";
import { HttpErrorResponse } from "@angular/common/http";
import { MatTableDataSource } from "@angular/material/table";
import { MatPaginator } from "@angular/material/paginator";
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-all-employees',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  public searchEmployee: Employee;

  employeeIdSearchDisabled = true;
  projectIdSearchDisabled = true;

  constructor(private employeeService: EmployeeService) { }

  displayedColumns: string[] = ['empId', 'empName', 'empSalary', 'projectId', 'empEmail', 'actions'];
  dataSource = new MatTableDataSource<Employee>();

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.employeeService.getEmployees().subscribe(
      (response: Employee[]) => {
        this.dataSource.data = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddEmloyee(addForm: NgForm): void {
    document.getElementById('add-employee-form').click();
    this.employeeService.addEmployee(addForm.value).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
        addForm.reset();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateEmloyee(employee: Employee): void {
    this.employeeService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmloyee(employeeId: number): void {
    this.employeeService.deleteEmployee(employeeId).subscribe(
      (response: void) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSearchById(employee: Employee): void {
    console.log(employee.empId);
    if (employee.empId) {
      this.employeeService.getEmployeeById(employee.empId).subscribe(
        (response: Employee) => {
          console.log(response);
          this.dataSource.data = [response];
        },
        (error: HttpErrorResponse) => {
          // alert(error.message);
          alert("No such Employee with employee id: " + employee.empId);
        }
      );
    }
    if (employee.projectId) {
      this.employeeService.getEmployeeByProjectId(employee.projectId).subscribe(
        (response: Employee[]) => {
          console.log(response);
          this.dataSource.data = response;
        },
        (error: HttpErrorResponse) => {
          // alert(error.message);
          alert("No such Employees with project id: " + employee.projectId);
        }
      );
    }

  }

  // applyFilter(event: Event) {
  //   const filterValue = (event.target as HTMLInputElement).value;
  //   this.dataSource.filter = filterValue.trim().toLowerCase();
  // }

  public toggle(mode: string) {
    if (mode === 'empId') {
      this.employeeIdSearchDisabled = false;
      this.projectIdSearchDisabled = true;
    }
    if (mode === 'projectID') {
      this.projectIdSearchDisabled = false;
      this.employeeIdSearchDisabled = true;
    }
    if (mode === 'all') {
      this.projectIdSearchDisabled = true;
      this.employeeIdSearchDisabled = true;
      this.getEmployees();
    }
  }

  public onOpenModal(employee: Employee, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addEmployeeModal');
    }
    if (mode === 'edit') {
      this.editEmployee = employee;
      button.setAttribute('data-target', '#updateEmployeeModal');
    }
    if (mode === 'delete') {
      this.deleteEmployee = employee;
      button.setAttribute('data-target', '#deleteEmployeeModal');
    }
    container.appendChild(button);
    button.click();
  }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
