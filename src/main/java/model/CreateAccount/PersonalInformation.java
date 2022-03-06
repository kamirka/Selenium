package model.CreateAccount;

import lombok.Data;

@Data
public class PersonalInformation {
        private String firstName;
        private String lastName;
        private String password;
        private Integer dateOfBirthDays;
        private String dateOfBirthMonths;
        private String dateOfBirthYears;
}
