import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AllEmployeesComponent} from './modules/employee/all-employees/all-employees.component'

const routes: Routes = [
  {
    path: "employee",
    component: AllEmployeesComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [AllEmployeesComponent]
