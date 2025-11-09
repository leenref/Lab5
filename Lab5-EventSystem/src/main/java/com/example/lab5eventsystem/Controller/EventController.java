package com.example.lab5eventsystem.Controller;

import com.example.lab5eventsystem.ApiResponse.ApiResponse;
import com.example.lab5eventsystem.Model.Event;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    ArrayList<Event> events=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Event> getStudents(){
        return events;

    }

    @PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Event event){
        events.add(event);
        return new ApiResponse("Event has been added");

    }

    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@PathVariable String id, @RequestBody Event event){
        for(int i=0; i< events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.set(i, event);
                return new ApiResponse("Events has been updated");
            }
        }
        return new ApiResponse("Events not found");

    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteStudent(@PathVariable String id){
        for(int i=0; i< events.size(); i++){
            if(events.get(i).getId().equals(id)){
                events.remove(i);
                return new ApiResponse("Events has been deleted");
            }
        }
        return new ApiResponse("Event not found");
    }

    @GetMapping("/search/{id}")
    public ApiResponse search(@PathVariable String id ){
        for(Event e : events){
            if(e.getId().equals(id)){
                return new ApiResponse("Event details: "+ e);
            }
        }
        return new ApiResponse("Event not found");
    }
    
    @PutMapping("/changeC/{id}/{capacity}")
    public ApiResponse changeCapacity(@PathVariable String id, @PathVariable int capacity) {
        for (Event e : events) {
            if (e.getId().equals(id)) {
                e.setCapacity(capacity);
                return new ApiResponse("Capacity has changed");
            }
        }
        return new ApiResponse("Event not found");
    }


}
