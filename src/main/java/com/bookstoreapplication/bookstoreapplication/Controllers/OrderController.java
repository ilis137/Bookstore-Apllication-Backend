package com.bookstoreapplication.bookstoreapplication.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookstoreapplication.bookstoreapplication.DTO.ResponseDTO;
import com.bookstoreapplication.bookstoreapplication.Exception.CartItemNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Exception.UserNotFoundException;
import com.bookstoreapplication.bookstoreapplication.Services.IOrderService;
import com.bookstoreapplication.bookstoreapplication.Services.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {

  @Autowired
  IOrderService orderService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> addOrder(@RequestParam("user_id") int user_id) throws UserNotFoundException, CartItemNotFoundException {
    ResponseDTO responseDTO = ResponseDTO.Build("Successfully created order for user with Id " + user_id,
        orderService.addOrder(user_id));
    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
  }

  @PostMapping("/remove")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> cancelOrder(@RequestParam("order_id") int order_id) throws UserNotFoundException, CartItemNotFoundException {
    orderService.cancelOrder(order_id);
    ResponseDTO responseDTO = ResponseDTO.Build("Successfully canceled order with Id " + order_id,true);
    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
  }

  @GetMapping("/viewall")
  @PreAuthorize("hasRole('ROLE_SELLER') or hasRole('ROLE_USER')")
  public ResponseEntity<ResponseDTO> viewOrders(@RequestParam("user_id") int user_id) throws UserNotFoundException, CartItemNotFoundException {
    ResponseDTO responseDTO = ResponseDTO.Build("Successfully fetched all orders of user " + user_id,orderService.getAllOrder(user_id));
    return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
  }


}
