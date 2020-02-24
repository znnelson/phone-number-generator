import { Component, OnInit, Input } from '@angular/core';
import { PhoneNumberGeneratorService } from '../services/phone-number-generator.service';
import { IPhoneNumber } from '../services/IPhoneNumber';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-paged-list',
  templateUrl: './app-paged-list.component.html',
  styleUrls: ['./app-paged-list.component.scss']
})
export class AppPagedListComponent implements OnInit {

  @Input('phoneNumber') phoneNumber: string; 
  show: boolean;
  pageNumber: number=1;
  phoneNumberList: IPhoneNumber[];
  pageSizeOptions: Array<number>=[50,100,200];
  pageSize: number = 50;
  subscribtion: Subscription;

  constructor(private phoneNumberService: PhoneNumberGeneratorService) {
    this.subscribtion=this.phoneNumberService.getShowData().subscribe(
      show=>{
        this.show=show
        if(show){
          this.getData();
        }
      }
    )
   }

  ngOnInit(): void {

  }

  getData(){
    this.phoneNumberService.getCombination(this.phoneNumber,this.pageSize,this.pageNumber).subscribe(
      (res)=>{this.phoneNumberList=res;},
      (error)=>{this.phoneNumberList=[]}
    )
  }

  onPageChange(dir: number){
    if(dir<0){
      if(this.pageNumber-1<1){
        this.pageNumber=1;
      }else{
        this.pageNumber=this.pageNumber-1;
        this.getData();
      }
    }else{
      this.pageNumber=this.pageNumber+1;
      this.getData();
    }
  }

  onPageSizeChange(value: number){
    this.getData();
  }

}
