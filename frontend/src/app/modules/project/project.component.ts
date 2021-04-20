import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ManagerService } from 'src/app/core/http/manager.service';
import { ProjectService } from 'src/app/core/http/project.service';
import { Manager } from '../manager/manager';
import { Project } from './project';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  public project: Project[];
  public editProject: Project;
  public deleteProject: Project;
  public searchProject: Project;
  public managers: Manager[];

  projectIdSearchDisabled = true;
  managerIdSearchDisabled = true;

  constructor(private projectService: ProjectService, private managerService: ManagerService) { }

  displayedColumns: string[] = ['projectId', 'projectName', 'projectLocation', 'managerId', 'projectCost', 'actions'];
  dataSource = new MatTableDataSource<Project>();

  ngOnInit(): void {
    this.getProjects();
  }

  public getManagers(): void {
    this.managerService.getManagers().subscribe(
      (response: Manager[]) => {
        this.managers = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getProjects(): void {
    this.projectService.getProjects().subscribe(
      (response: Project[]) => {
        response.forEach((element) => {
          this.projectService.getProjectCostByProjectId(element.projectId).subscribe(
            (res: number) => {
              element.projectCost = res;
            }
          );
        });
        this.dataSource.data = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onAddProject(addForm: NgForm): void {
    document.getElementById('add-project-form').click();
    this.projectService.addProject(addForm.value).subscribe(
      (response: Project) => {
        console.log(response);
        this.getProjects();
        addForm.reset();
        alert("Project added successfully");
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
        addForm.reset();
      }
    );
  }

  public onUpdateEmloyee(project: Project): void {
    this.projectService.updateProject(project).subscribe(
      (response: Project) => {
        console.log(response);
        this.getProjects();
        alert("Project updated successfully");
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    );
  }

  public onDeleteProject(projectId: number): void {
    this.projectService.deleteProject(projectId).subscribe(
      (response: void) => {
        console.log(response);
        this.getProjects();
        alert("Delete Project successful");
      },
      (error: HttpErrorResponse) => {
        alert(error.error.message);
      }
    );
  }

  public onSearchById(project: Project): void {
    console.log(project.projectId);
    if (project.projectId) {
      this.projectService.getProjectById(project.projectId).subscribe(
        (response: Project) => {
          console.log(response);
          this.dataSource.data = [response];
        },
        (error: HttpErrorResponse) => {
          // alert(error.message);
          alert("No such Project with project id: " + project.projectId);
        }
      );
    }
    if (project.managerId) {
      this.projectService.getProjectByManagerId(project.managerId).subscribe(
        (response: Project[]) => {
          console.log(response);
          this.dataSource.data = response;
        },
        (error: HttpErrorResponse) => {
          // alert(error.message);
          alert("No such project with manager id:" + project.managerId);
        }
      );
    }
  }

  public toggle(mode: string) {
    if (mode === 'projectId') {
      this.projectIdSearchDisabled = false;
      this.managerIdSearchDisabled = true;
    }
    if (mode === 'managerId') {
      this.managerIdSearchDisabled = false;
      this.projectIdSearchDisabled = true;
    }
    if (mode === 'all') {
      this.projectIdSearchDisabled = true;
      this.managerIdSearchDisabled = true;
      this.getProjects();
    }
  }

  public onOpenModal(project: Project, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      this.getManagers();
      button.setAttribute('data-target', '#addProjectModal');
    }
    if (mode === 'edit') {
      this.getManagers();
      this.editProject = project;
      button.setAttribute('data-target', '#updateProjectModal');
    }
    if (mode === 'delete') {
      this.deleteProject = project;
      button.setAttribute('data-target', '#deleteProjectModal');
    }
    container.appendChild(button);
    button.click();
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
