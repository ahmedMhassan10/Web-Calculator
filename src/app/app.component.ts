import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CalculationsService } from './calculations.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  formText: String = "0";
  num1:String="a";
  num2: String = "a";
  type: String = "";
  flag: boolean = false;

  ngOnInit(): void {

  }

  constructor(private calculationsService: CalculationsService) {

  }

  doBinaryCalculations(type: String ) {
    console.log(this.num1,this.num2,this.type);
    if (this.formText == "Cannot divide by zero" || this.formText == "Invalid input"||this.formText=="Over flow") {
      this.clear();
      return;
    }
    if(this.flag && type !="equal"){
      this.type=type;
      this.formText = "0";
      return;
    }
    if(type=="equal"&&this.type=="")return;
    if (this.num1 == "a") {
      this.num1 = this.formText;
    } else if (this.flag) {
      this.num2 = this.formText;
    }

     if (this.flag || type == "equal") {
      this.solveFunction(2,this.type, this.num1, this.num2);
    }
    if (type != "equal") {
      this.flag = true;
      this.type = type;
      this.formText = "0";

    }
  }

  doUnaryOperation(type: String) {

    if (this.formText == "Cannot divide by zero" || this.formText == "Invalid input"||this.formText=="Over flow") {
      this.clear();
      return;
    }else if (type == "equal") { return; }

    this.solveFunction(1,type, this.formText, this.num2);
  }

  clear() {
      this.formText = "0";
      this.num2=this.num1 = "a"
      this.type = "";
      this.flag = false;

  }

  solveFunction(fn:number,type: String,num1:String,num2:String) {
    this.calculationsService.solveFunction(type,num1,num2).subscribe((response: String) => {
      this.formText = response
      if(fn==2){
        this.flag=false;
        this.num1=this.formText;

      }
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  delete(num: String) {
    if (this.formText == "Cannot divide by zero" || this.formText == "Invalid input"||this.formText=="Over flow") {
      this.clear();return;
    }
        this.calculationsService.delete(this.formText).subscribe((response: String) => {
          this.formText = response;
    },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  addDigit(digit: String) {
    if (this.formText == "Cannot divide by zero" || this.formText == "Invalid input"||this.formText=="Over flow" || (this.flag==false&&this.type!="")) {
      this.clear();
    }

    this.calculationsService.addDigit(this.formText, digit).subscribe((response: String) => {
        this.formText = response;
        },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
}
