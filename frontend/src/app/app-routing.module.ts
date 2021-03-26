import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
// import {allEmployees} from './modules/employee/all-employees/all-employees.component'

const routes: Routes = [
  // {
  //   path: "employee",
  //   component: allEmployees
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
