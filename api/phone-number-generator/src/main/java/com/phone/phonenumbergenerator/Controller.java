package com.phone.phonenumbergenerator;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
@CrossOrigin
public class Controller{

    @GetMapping("/test")
    public @ResponseBody ApiStatus test(){
        ApiStatus status = new ApiStatus();
        status.status="success";
        return status;
    }

    @PostMapping("/validation")
    public @ResponseBody PhoneNumberValidation validNumber(@RequestBody @Valid PhoneNumberEntity number){
        PhoneNumberValidation v = new PhoneNumberValidation();
        if(!validation(number.phoneNumber)){
            v.isValid=false;
            return v;
        }
        
        Generator g = new Generator(number.phoneNumber);
        v.isValid=true;
        v.totalCombination=g.getValidCombinationTotalNumber();
        return v;
    }

    @GetMapping(value = "/combinations")
    public @ResponseBody List<PhoneNumberEntity> getCombinations(@RequestParam("phoneNumber") String number, @RequestParam("pageSize") int pageSize, @RequestParam("pageNumber") int pageNumber) {
        Generator g = new Generator(number);

        return g.getCombinationResult(number, pageSize, pageNumber);
    }
    

    private boolean validation(String phoneNumber){
        if(phoneNumber.length()!=10 && phoneNumber.length()!=7){
            return false;
        }
        
        if (!phoneNumber.matches("[0-9]+")){
            return false;
        }

        if(phoneNumber.charAt(0)=='0' || phoneNumber.charAt(0)=='1'){
            return false;
        }

        if(phoneNumber.length()==10 && (phoneNumber.charAt(3)=='0' || phoneNumber.charAt(3)=='1')){
            return false;
        }
        
        return true;
    }


}