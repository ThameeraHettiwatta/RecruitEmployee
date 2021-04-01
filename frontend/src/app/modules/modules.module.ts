import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeComponent } from './employee/employee.component';
import { ProjectComponent } from './project/project.component';
import { ManagerComponent } from './manager/manager.component';


@NgModule({
  declarations: [
    EmployeeComponent,
    ProjectComponent,
    ManagerComponent],
  imports: [
    CommonModule
  ]
})
export class ModulesModule { }
