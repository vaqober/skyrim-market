import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ClientComponent } from './client.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { OrderComponent } from './order/order.component';
import { PickpocketingFormComponent } from '@modules/client/order/pickpocketing-form/pickpocketing-form.component';
import { OrderGuard } from '@guards/order.guard';
import { OrdersComponent } from "@modules/client/orders/orders.component";

const routes: Routes = [
  {
    path: '',
    component: ClientComponent,
    children: [
      {
        path: '',
        component: TopProductsComponent
      },
      {
        path: 'order',
        component: OrderComponent,
        children: [
          {
            path: ''
          },
          {
            path: 'pickpocketing',
            component: PickpocketingFormComponent,
            canActivate: [OrderGuard],
          }
        ]
      },
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'top-products',
        component: TopProductsComponent
      },
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
