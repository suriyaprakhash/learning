import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeroComponent } from './hero/hero.component';
import { HttpClientModule } from '@angular/common/http';
import { ConfigConsumerComponent } from './config-consumer/config-consumer.component';

@NgModule({
  declarations: [
    AppComponent,
    HeroComponent,
    ConfigConsumerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
