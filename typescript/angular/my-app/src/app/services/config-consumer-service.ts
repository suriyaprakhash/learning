import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigConsumerService<T> {

  constructor(private httpClient : HttpClient) {}

  getConfigUrl(): Observable<T> {
    return this.httpClient.get<T>('https://gist.githubusercontent.com/suriyaprakhash/6c7eaeb369d1046a76e74e4b6cfe92dc/raw/4e97ca163cf4d3b791c5209d1900109c526ee320/Config.json');
  }

  getConfigFromFilePath() {
    
  }

}
