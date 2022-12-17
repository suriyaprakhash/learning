import { Component, IterableDiffers } from '@angular/core';
import { HeroHttpService } from '../services/hero-http.service';
import { iif,of, Observable, filter, map, concatMap, tap, interval } from 'rxjs';
import { Router } from '@angular/router';

interface Product {
  id? : number,
  title : string
}

@Component({
  selector: 'app-hero',
  templateUrl: './hero.component.html',
  styleUrls: ['./hero.component.scss']
})
export class HeroComponent {

  constructor(private router: Router) {
    console.log('inside constructor');
  }

  ngOnInit() {
    console.log('inside ngOnInit');
  }

  goBack() {
      this.router.navigate(['']);
  }

  products: Product[] = [{
    id: 1,
    title: 'hello'
  }, {
    id: 2,
    title: 'world'
  }, 
   {
    id: 3,
    title: 'phone'
  }];

  count: number = 0;
  
  filterProducts = this.products.filter(product => product.id && product.id % 2 !== 0);

  myProducts$: Observable<Product[]> = of(this.filterProducts);

  filterdProduct$ = this.myProducts$.pipe(
    filter(products => products.some(product => product.id === 1)));

  firstCount$ : Observable<number> = of(10);
  secondCount$ : Observable<number> = of(5000);

  printDataInInterval = (data: string, interval: number)  => {
    setInterval(() => console.log(data), interval);
  };
  // helloCount$ = this.firstCount$
  //   .pipe(
  //     tap((firstCount: number) => console.log(firstCount)),
  //     concatMap(firstCount => 
  //     (firstCount > -1) ? of(firstCount) : this.secondCount$));

  helloCount$ = this.firstCount$
    .pipe(
      tap((firstCount: number) => {
        console.log('tapping firstCount : ' + firstCount);
        // this.printDataInInterval(firstCount + ' - data', 1000);
      }),
      map(firstCount => firstCount + 1),
      concatMap(firstCount => iif(() => firstCount > 5, of(firstCount), this.secondCount$)));

  // subscribe() {
    // this.helloCount$ = this.firstCount$
    // .pipe(
    //   tap((firstCount: number) => {
    //     console.log(firstCount);
    //     // this.printDataInInterval(firstCount + ' - data', 1000);
    //   }),
    //   map(firstCount => firstCount + 1),
    //   concatMap(firstCount => iif(() => firstCount > 5, of(firstCount), this.secondCount$)));
  // }      
  
}
