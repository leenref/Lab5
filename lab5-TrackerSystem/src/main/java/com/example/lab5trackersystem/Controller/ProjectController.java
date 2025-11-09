package com.example.lab5trackersystem.Controller;

import com.example.lab5trackersystem.ApiResponse.ApiResponse;
import com.example.lab5trackersystem.Model.Project;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    ArrayList<Project> projects=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Project> getStudents(){
        return projects;

    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Project project){
        projects.add(project);
        return new ApiResponse("project has been added");

    }

    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@PathVariable String id, @RequestBody Project project){
        for(int i=0; i< projects.size(); i++){
            if(projects.get(i).getId().equals(id)){
                projects.set(i, project);
                return new ApiResponse("project has been updated");
            }
        }
        return new ApiResponse("project not found");

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id){
        for(int i=0; i< projects.size(); i++){
            if(projects.get(i).getId().equals(id)){
                projects.remove(i);
                return new ApiResponse("Project has been deleted");
            }
        }
        return new ApiResponse("Project not found");
    }


    @PutMapping("/changeS/{id}/{statues}")
    public ApiResponse changeStatues(@PathVariable String id, @PathVariable String statues) {
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                if (project.getStatus().equalsIgnoreCase(statues)) {
                    return new ApiResponse("Task status is already marked true ");
                } else {
                    project.setStatus(statues);
                    return new ApiResponse("Task status updated successfully");
                }
            }
        }
        return new ApiResponse("Project not found");
    }

    
    @GetMapping("/search/{title}")
    public ApiResponse search(@PathVariable String title){
        for(Project p : projects){
            if(p.getTitle().equals(title)){
                return new ApiResponse("Project details: "+ p);
            }
        }
        return new ApiResponse("Project not found");
    }


    @GetMapping("/AllProjects/{companyName}")
    public ApiResponse allProject(@PathVariable String companyName){
        ArrayList<Project> projectsByName = new ArrayList<>();

        for (Project p : projects) {
            if (p.getCompanyName().equals(companyName)) {
                projectsByName.add(p);
            }
        }

        if (!projectsByName.isEmpty()) {
            return new ApiResponse("Project details: " + projectsByName);
        } else {
            return new ApiResponse("Project not found");
        }
    }


}
