import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders,HttpParams } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { IApiStatus } from './IApiStatus';
import { environment } from 'src/environments/environment';
import { IPhoneNumberValidation } from './IPhoneValidation';
import { IPhoneNumber } from './IPhoneNumber';

@Injectable({
  providedIn: 'root'
})
export class PhoneNumberGeneratorService {
  private showCombination = new Subject<boolean>();

  constructor(private http: HttpClient) { }

  test():Observable<IApiStatus>{
    return this.http.get<IApiStatus>(environment.api.domain+"test",{headers: new HttpHeaders({'access-control-allow-origin': '*'})});
  }

  validPhoneNumber(phoneNumber: string):Observable<IPhoneNumberValidation>{
    return this.http.post<IPhoneNumberValidation>(environment.api.domain+'validation', {phoneNumber: phoneNumber},{headers: new HttpHeaders({'access-control-allow-origin': '*'})});
  }

  getCombination(phoneNumber: string, pageSize: number, pageNumber: number):Observable<IPhoneNumber[]>{
    const params = new HttpParams().set('phoneNumber', phoneNumber).set('pageSize', pageSize.toString()).set('pageNumber', pageNumber.toString());
   
    return this.http.get<IPhoneNumber[]>(environment.api.domain+'combinations', {headers: new HttpHeaders({'access-control-allow-origin': '*'}), params})
  }

  showData(show:boolean){
      this.showCombination.next(show);
  }

  getShowData():Observable<boolean>{
    return this.showCombination.asObservable();
  }

}
