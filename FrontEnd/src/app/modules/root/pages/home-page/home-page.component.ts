import { Component, OnInit } from '@angular/core';
 
import { Observable } from 'rxjs';
 
import { EventsService, EventsTypes } from '../../../core/services/events.servide';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})

export class HomePageComponent implements OnInit {
 

  constructor( 
              private eventsService: EventsService) {
    // @ts-ignore
    if (window.Cypress) {
      // @ts-ignore
      window.HomePageComponent = this
    }
  }

  ngOnInit() {
   
  }
}
