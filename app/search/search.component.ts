import { Component, EventEmitter, Output } from '@angular/core';
// Initialization for ES Users


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchText: string = '';

  @Output() 
  searchEvent:EventEmitter<string>=new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }
  movieSearch() {    
    this.searchEvent.emit(this.searchText);
  }
}
