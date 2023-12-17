import { Component, OnInit } from '@angular/core';
import { ActiveAnalogFilterService } from '../active-analog-filter.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-filter-types',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './filter-types.component.html',
  styleUrl: './filter-types.component.css'
})
export class FilterTypesComponent implements OnInit {

  filterTypes: string[] | undefined;

  constructor(private filterService: ActiveAnalogFilterService) {
  }

  ngOnInit() {
    this.filterService.findAll().subscribe(data => {
      this.filterTypes = data;
    });
  }

}
