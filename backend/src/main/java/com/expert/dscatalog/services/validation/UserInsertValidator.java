package com.expert.dscatalog.services.validation;

import com.expert.dscatalog.dto.UserInsertDTO;
import com.expert.dscatalog.entities.User;
import com.expert.dscatalog.repositories.UserRepository;
import com.expert.dscatalog.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repo;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        // Coloque aqui seus testes de validação, acrescentando objetos FieldMessage à lista

        User user = repo.findByEmail( dto.getEmail() );

        if( user != null ){
            list.add( new FieldMessage( "email", "Esse email já existe." ) );
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}