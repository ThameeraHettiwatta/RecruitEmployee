import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Project } from 'src/app/modules/project/project';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiServerUrl}/projects`);
  }

  public addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(`${this.apiServerUrl}/projects`, project);
  }

  public updateProject(project: Project): Observable<Project> {
    // console.log(project);
    return this.http.put<Project>(`${this.apiServerUrl}/projects`, project);
  }

  public deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/projects/${projectId}`);
  }

  public getProjectById(projectId: number): Observable<Project> {
    return this.http.get<Project>(`${this.apiServerUrl}/projects/${projectId}`);
  }

  public getProjectByManagerId(managerId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiServerUrl}/projects/${managerId}/projects`);
  }

  public getProjectCostByProjectId(projectId: number): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/projects/projectCost/${projectId}`)
  }
}
