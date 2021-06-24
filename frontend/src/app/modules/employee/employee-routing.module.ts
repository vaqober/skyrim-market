import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {ClientComponent} from "@modules/client/client.component";
import {TopProductsComponent} from "@modules/client/top-products/top-products.component";
import {OrderComponent} from "@modules/client/order/order.component";
import {PickpocketingFormComponent} from "@modules/client/order/pickpocketing-form/pickpocketing-form.component";
import {OrderGuard} from "@guards/order.guard";
import {OrdersComponent} from "@modules/client/orders/orders.component";
import {EmployeeModule} from "@modules/employee/employee.module";
import {AvailableOrdersComponent} from "@modules/employee/available-orders/available-orders.component";
import {EmployeeComponent} from "@modules/employee/employee.component";
import {MyOrdersComponent} from "@modules/employee/my-orders/my-orders.component";
import {MyOrderComponent} from "@modules/employee/my-order/my-order.component";
import {AvailableOrderComponent} from "@modules/employee/available-order/available-order.component";

const routes: Routes = [
  {
    path: '',
    component: EmployeeComponent,
    children: [
      {
        path: 'my-orders',
        component: MyOrdersComponent
      },
      {
        path: 'my-order',
        component: MyOrderComponent
      },
      {
        path: 'available-orders',
        component: AvailableOrdersComponent
      },
      {
        path: 'available-order',
        component: AvailableOrderComponent
      }
      ]
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }
