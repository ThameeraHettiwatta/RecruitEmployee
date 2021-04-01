import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {EmployeeComponent} from './modules/employee/employee.component'
import {ProjectComponent} from './modules/project/project.component'
import {ManagerComponent} from './modules/manager/manager.component'

const routes: Routes = [
  { path: "employee", component: EmployeeComponent },
  { path: "project", component: ProjectComponent },
  { path: "manager", component: ManagerComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [EmployeeComponent]
