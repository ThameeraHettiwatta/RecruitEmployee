import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './modules/employee/employee.component'
import { ProjectComponent } from './modules/project/project.component'
import { ManagerComponent } from './modules/manager/manager.component'
import { HomeComponent } from './modules/home/home.component';
const routes: Routes = [
  { path: "employee", component: EmployeeComponent },
  { path: "project", component: ProjectComponent },
  { path: "manager", component: ManagerComponent },
  { path: "home", component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [EmployeeComponent, ProjectComponent, ManagerComponent, HomeComponent]
