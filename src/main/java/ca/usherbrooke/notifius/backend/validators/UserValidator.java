/*
 * Copyright (c) 2020. Universitée de Sherbrooke, All rights reserved.
 */

package ca.usherbrooke.notifius.backend.validators;

import ca.usherbrooke.notifius.backend.models.User;
import ca.usherbrooke.notifius.backend.resterrors.UserIdMalformedException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidator
{
    //todo user validator
    public void validUserThrowIfNotValid(User user)
    {
        if (!Pattern.compile("^[a-z]{4}[0-9]{4}$").matcher(user.getId()).matches())
            throw new UserIdMalformedException();
    }
}
