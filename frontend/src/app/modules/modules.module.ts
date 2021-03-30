import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AllEmployeesComponent } from './employee/all-employees/all-employees.component';
import { CreateEmployeeComponent } from './employee/create-employee/create-employee.component';
import {EmployeeModule} from "./employee/employee.module";


@NgModule({
  declarations: [
      // AllEmployeesComponent,
      // CreateEmployeeComponentZZZ
  ],
  imports: [
    CommonModule
      // EmployeeModule
  ]
})
export class ModulesModule { }
