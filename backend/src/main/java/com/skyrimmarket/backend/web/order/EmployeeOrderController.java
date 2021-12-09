package com.skyrimmarket.backend.web.order;

import com.skyrimmarket.backend.model.order.Order;
import com.skyrimmarket.backend.service.order.EmployeeOrderService;
import com.skyrimmarket.backend.web.form.EmployeeOrderForm;
import com.skyrimmarket.backend.web.form.MasterOrderForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@PreAuthorize("hasRole('ROLE_EMPLOYEE')")
@RestController
@RequestMapping("/api/order/contractor")
@RequiredArgsConstructor
public class EmployeeOrderController {
    private final EmployeeOrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getContractorOrders(@PathVariable("id") Long id) {
        return ok(orderService.getContractorOrders(id));
    }

    @GetMapping("/payed")
    public ResponseEntity<List<Order>> getPayedOrders() {
        return ok(orderService.getPayedOrders());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> setOrderToStudent(@PathVariable("id") Long orderId,
                                                         @RequestBody EmployeeOrderForm form) {
        return ok(orderService.setOrderToStudent(orderId, form.getContractor()));
    }
}
