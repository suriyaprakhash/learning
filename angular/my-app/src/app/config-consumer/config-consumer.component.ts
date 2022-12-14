import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { ConfigConsumerService } from '../services/config-consumer-service';
import { ConfigData } from './config-data';

@Component({
  selector: 'app-config-consumer',
  templateUrl: './config-consumer.component.html',
  styleUrls: ['./config-consumer.component.scss']
})
export class ConfigConsumerComponent {

  constructor(private configConsumerService: ConfigConsumerService<ConfigData>) {}

  readonly configData$ = this.configConsumerService.getConfigUrl()
        .pipe(tap((data: ConfigData) => {
          console.log('configData' + data);
        }));

}
