import { Component, OnInit } from '@angular/core';
import { PhoneNumberGeneratorService } from '../services/phone-number-generator.service';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { IApiStatus } from '../services/IApiStatus';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IPhoneNumberValidation } from '../services/IPhoneValidation';

@Component({
  selector: 'app-input',
  templateUrl: './app-input.component.html',
  styleUrls: ['./app-input.component.scss']
})
export class AppInputComponent implements OnInit {
  text: Observable<String>;
  statusCheck: boolean = false;
  checkPhoneNumberForm: FormGroup;
  phoneNumberValidation: IPhoneNumberValidation;
  showComb: boolean = false;
  constructor(private phoneNumberService: PhoneNumberGeneratorService, private formBuilder: FormBuilder) {
    this.checkPhoneNumberForm = this.formBuilder.group({
      phoneNumber:''
    });
    this.phoneNumberValidation = {isValid:false, totalCombination:0};
   }

  ngOnInit(): void {
    this.text=this.phoneNumberService.test().pipe(
      map((res: IApiStatus ) => {this.statusCheck=true; return res.status;}),
      catchError(error=> {this.statusCheck=true; return of('api service is down')})
      );
    this.onFormChanges();
  }

  onFormChanges(): void {
    this.checkPhoneNumberForm.get('phoneNumber').valueChanges.subscribe(val => {
      this.phoneNumberService.showData(false);
      this.phoneNumberValidation.isValid=false;
      this.phoneNumberValidation.totalCombination=0;
    });
  }
  onSubmit(forvalue){
    this.phoneNumberService.validPhoneNumber(forvalue.phoneNumber).subscribe(
      (res)=>{
        this.phoneNumberValidation.isValid=res.isValid;
        this.phoneNumberValidation.totalCombination=res.totalCombination;
    },
    (error)=> {
      this.phoneNumberValidation.isValid=false;
      this.phoneNumberValidation.totalCombination=0;
    })
    console.log(forvalue.phoneNumber);
  }

  onShowCombinations(){
    this.phoneNumberService.showData(true);
  }

}
