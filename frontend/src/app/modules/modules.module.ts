import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmployeeComponent } from './employee/employee.component';
import { ProjectComponent } from './project/project.component';
import { ManagerComponent } from './manager/manager.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatTableModule} from "@angular/material/table";


@NgModule({
  declarations: [
    // EmployeeComponent,
    // ProjectComponent,
    // ManagerComponent
  ],
    imports: [
        // CommonModule,
        // MatPaginatorModule,
        // MatTableModule
    ]
})
export class ModulesModule { }
