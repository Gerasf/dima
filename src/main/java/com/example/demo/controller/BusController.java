package com.example.demo.controller;


import com.example.demo.entity.Bus;
import com.example.demo.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusController {
    @Autowired
    private BusService busService;
    @Async
    @GetMapping("/")
    public String getMainPage(Model model){
        model.addAttribute("list",busService.getAllList());
        return "mainpage";
    }
    @Async
    @GetMapping("/edit/{id}")
    public String getEditForCountPassengers(@PathVariable("id") String id,Model model){
        model.addAttribute("bus",busService.getById(id));
        return "editCountPassengers";
    }
    @Async
    @GetMapping("doors/{id}")
    public String doorService(@PathVariable("id") String id){
        busService.switchStatus(id);
        return "redirect:/edit/"+id;
    }
    @Async
    @PostMapping("countPassengers/{id}")
    public String countPassengers(@PathVariable("id") String id, @RequestParam(value = "passengersNow",required = false) String passengersNow, Model model){
        Bus bus = busService.getById(id);
        if(bus.getDoor()) {
            bus.setPassengersNow(passengersNow);
            busService.update(bus);
            return "redirect:/";
        }
        model.addAttribute("id",id);
        return "errorDoors";
    }
    @Async
    @GetMapping("admin")
    public String getListForAdmin(Model model){
        model.addAttribute("list",busService.getAllList());
        return "adminPanel";
    }
    @Async
    @GetMapping("/admin/delete/{id}")
    public String delete(@PathVariable("id") String id){
        busService.delete(id);
        return "redirect:/admin";
    }
    @Async
    @GetMapping("admin/edit/{id}")
    public String editBus(@PathVariable("id") String id,Model model){
        model.addAttribute("bus",busService.getById(id));
        return "editBus";
    }
    @Async
    @PostMapping("admin/{id}")
    public String saveData(@PathVariable("id") String id,
            @RequestParam(value = "number",required = false) String number,
            @RequestParam(value = "brand",required = false) String brand,
            @RequestParam(value = "year",required = false) String year,
            @RequestParam(value = "numberRout",required = false) String numberRout,
            @RequestParam(value = "seating",required = false) String seating,
            @RequestParam(value = "totalCapacity",required = false) String totalCapacity,
            @RequestParam(value = "passengersNow", required = false) String passengersNow,
            @RequestParam(value = "door", required = false) String door
    ){
        Bus bus = new Bus(Long.parseLong(id),number,brand,year,numberRout,seating,totalCapacity,passengersNow,Boolean.valueOf(door));
        busService.update(bus);
        return "redirect:/admin";
    }
    @Async
    @GetMapping("/admin/new")
    public String newEmployee(){
        busService.saveNew();
        return "redirect:/admin";
    }
}
