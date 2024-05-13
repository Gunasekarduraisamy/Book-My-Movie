import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  totalBillAmount: number;
  transactionID: string;

  constructor(private http: HttpClient) {}

  processPayment(user: any): Observable<PaymentResponse> {
    return this.http.post<PaymentResponse>('/api/payments', user);
  }
}
