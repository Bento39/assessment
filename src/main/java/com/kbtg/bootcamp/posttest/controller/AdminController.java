package com.kbtg.bootcamp.posttest.controller;


import com.kbtg.bootcamp.posttest.request.AdminRequest;


import com.kbtg.bootcamp.posttest.response.AdminResponse;
import com.kbtg.bootcamp.posttest.service.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //EXP01
    @PostMapping("/lotteries")
    public AdminResponse addLottery(@Validated @RequestBody AdminRequest request){
        return this.adminService.createLottery(request) ;
    }


}
