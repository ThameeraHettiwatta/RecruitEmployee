import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ManagerService } from 'src/app/core/http/manager.service';
import { Employee } from '../employee/employee';
import { Manager } from './manager';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {

  public managers: Manager[];
  public editManager: Manager;
  public deleteManager: Manager;
  public searchManager: Manager;

  managerIdSearchDisabled = true;

  constructor(private managerService: ManagerService) { }

  displayedColumns: string[] = ['managerId', 'managerName', 'managerEmail', 'managerContactNumber', 'actions'];
  dataSource = new MatTableDataSource<Manager>();

  ngOnInit() {
    this.getManagers();
  }

  public getManagers(): void {
    this.managerService.getManagers().subscribe(
      (response: Manager[]) => {
        this.dataSource.data = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onAddManager(addForm: NgForm): void {
    document.getElementById('add-manager-form').click();
    this.managerService.addManager(addForm.value).subscribe(
      (response: Manager) => {
        console.log(response);
        this.getManagers();
        addForm.reset();
        alert("Successfully added Manager");
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateManager(manager: Manager): void {
    this.managerService.updateManager(manager).subscribe(
      (response: Manager) => {
        console.log(response);
        this.getManagers();
        alert("Successfully updated Manager: " + manager.managerName);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onDeleteManager(managerId: number): void {
    this.managerService.deleteManager(managerId).subscribe(
      (response: void) => {
        console.log(response);
        this.getManagers();
        alert("Successfully deleted Manager with Manager Id: " + managerId)
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onSearchById(manager: Manager): void {
    this.managerService.getManagerById(manager.managerId).subscribe(
      (response: Manager) => {
        console.log(response);
        this.dataSource.data = [response];
      },
      (error: HttpErrorResponse) => {
        // alert(error.message);
        alert("No such Manager with manager id: " + manager.managerId);
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
      this.getManagers();
    }
  }

  public onOpenModal(manager: Manager, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addManagerModal');
    }
    if (mode === 'edit') {
      this.editManager = manager;
      button.setAttribute('data-target', '#updateManagerModal');
    }
    if (mode === 'delete') {
      this.deleteManager = manager;
      button.setAttribute('data-target', '#deleteManagerModal');
    }
    container.appendChild(button);
    button.click();
  }


  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
