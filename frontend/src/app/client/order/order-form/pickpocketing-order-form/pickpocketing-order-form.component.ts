import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms'
import { Observable } from 'rxjs'
import { Title } from '@models/title'
import { TitleService } from '@services/title.service'
import { OrderFormComponent } from '@app/client/order/order-form/order-form.component'
import { Item } from '@models/Item'

@Component({
  selector: 'app-pickpocketing-order-form',
  templateUrl: './pickpocketing-order-form.component.html',
  styleUrls: [
    '../order-form.component.scss',
    './pickpocketing-order-form.component.scss'
  ]
})
export class PickpocketingOrderFormComponent extends OrderFormComponent {
  @Input()
  form: FormGroup

  @Input()
  disabled: boolean

  @Input()
  titles: Title[] = []

  @Input()
  items: Item[] = []
}
