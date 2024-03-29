import { Component, OnInit } from '@angular/core';
import { filter, map, switchMap, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { MatDialog } from '@angular/material/dialog';
import { EmployeeOrderService } from '@services/order/employee-order.service'
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'
import { showError, showNotification } from '@utils/notification-util'
import { toMessage } from '@utils/http-util'

@Component({
  selector: 'app-available-order',
  templateUrl: './available-order.component.html',
  styleUrls: ['./available-order.component.scss']
})
export class AvailableOrderComponent implements OnInit {
  order$: Observable<Order>
  private order: Order

  loading = true

  constructor(private activateRoute: ActivatedRoute,
              private store: Store,
              private orderService: OrderService,
              private employeeOrderService: EmployeeOrderService,
              private dialogService: MatDialog) {
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        filter(id => !!id),
        switchMap(id => this.orderService.get(id).pipe(withLoading(this))),
        tap(order => this.order = order)
      )
  }

  close() {
    this.store.dispatch(new Navigate(['/employee/available-orders']))
  }

  assignToMe() {
    this.employeeOrderService.assignToMe(this.order.id)
      .pipe(
        withLoading(this),
        map(order => order.id),
        tap(() => showNotification(this.dialogService, 'Order assigned to you!'))
      )
      .subscribe(
        id => this.store.dispatch(new Navigate([`/employee/my-order/${id}`])),
        error => showError(this.dialogService, toMessage(error))
      )
  }
}
