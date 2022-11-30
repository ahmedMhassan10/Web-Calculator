package Calculator.Main.controller;

import Calculator.Main.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    private final CalculatorService calculatorService;
    @Autowired
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/clear")
    public ResponseEntity<String> clear(){
        return new ResponseEntity<String>(calculatorService.clear(),HttpStatus.OK);
    }
    @GetMapping("/solveFunction/{type}/{num1}/{num2}")
    public ResponseEntity<String> solveFunction(@PathVariable("type") String type,@PathVariable("num1") String num1,@PathVariable("num2")String num2){
        return new ResponseEntity<String>(calculatorService.solveFunction(type,num1,num2),HttpStatus.OK);
    }
    @GetMapping("/delete/{num}")
    public ResponseEntity<String> delete( @PathVariable("num") String num){
        return new ResponseEntity<String>(calculatorService.delete(num),HttpStatus.OK);
    }


    @GetMapping("/addDigit/{num}/{digit}")
    public ResponseEntity<String> addDigit(@PathVariable("num") String num,@PathVariable("digit")String digit){
        return new ResponseEntity<String>(calculatorService.addDigit(num, digit), HttpStatus.OK);
    }
}
