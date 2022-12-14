import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConfigConsumerComponent } from './config-consumer/config-consumer.component';
import { HeroComponent } from './hero/hero.component';

const routes: Routes = [
  { path: 'hero', component: HeroComponent },
  { path: 'config-consumer', component: ConfigConsumerComponent }
 ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
