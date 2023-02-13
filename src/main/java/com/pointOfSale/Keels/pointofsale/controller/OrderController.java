package com.pointOfSale.Keels.pointofsale.controller;

import com.pointOfSale.Keels.pointofsale.dto.request.RequestOrderSaveDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/order")
public class OrderController {

    @PostMapping(path = {"/save-order"})
    public RequestOrderSaveDTO saveOrder(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
        System.out.println(requestOrderSaveDTO);
        return  requestOrderSaveDTO;
    }
}
