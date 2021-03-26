import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllEmployeesComponent } from './all-employees/all-employees.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';



@NgModule({
  declarations: [AllEmployeesComponent, CreateEmployeeComponent],
  imports: [
    CommonModule
  ]
})
export class EmployeeModule { }
