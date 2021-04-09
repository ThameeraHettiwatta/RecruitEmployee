import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ManagerService } from 'src/app/core/http/manager.service';
import { Employee } from '../employee/employee';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  public employees: Employee[];
  public editEmployee: Employee;
  public deleteEmployee: Employee;
  public searchEmployee: Employee;

  managerIdSearchDisabled = true;

  constructor(private managerService: ManagerService) { }

  displayedColumns: string[] = ['empId', 'empName', 'empSalary', 'projectId', 'empEmail', 'actions'];
  dataSource = new MatTableDataSource<Employee>();

  ngOnInit() {
    this.getEmployees();
  }

  public getEmployees(): void {
    this.managerService.getEmployees().subscribe(
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
    this.managerService.addEmployee(addForm.value).subscribe(
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
    this.managerService.updateEmployee(employee).subscribe(
      (response: Employee) => {
        console.log(response);
        this.getEmployees();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteEmloyee(managerId: number): void {
    this.managerService.deleteEmployee(managerId).subscribe(
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
    this.managerService.getEmployeeById(employee.empId).subscribe(
      (response: Employee) => {
        console.log(response);
        this.dataSource.data = [response];
      },
      (error: HttpErrorResponse) => {
        // alert(error.message);
        alert("No such Employee with manager id: " + employee.empId);
      }
    );
  }

  // applyFilter(event: Event) {
  //   const filterValue = (event.target as HTMLInputElement).value;
  //   this.dataSource.filter = filterValue.trim().toLowerCase();
  // }

  public toggle(mode: string) {
    if (mode === 'managerId') {
      this.managerIdSearchDisabled = false;
    }
    if (mode === 'all') {
      this.managerIdSearchDisabled = true;
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
