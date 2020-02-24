package com.phone.phonenumbergenerator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class PhoneNumberEntity {
    @NotBlank
    @NotEmpty
    public String phoneNumber;
}