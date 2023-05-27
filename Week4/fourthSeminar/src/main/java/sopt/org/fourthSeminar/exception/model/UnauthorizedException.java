package sopt.org.fourthSeminar.exception.model;

import org.apache.logging.log4j.message.Message;
import sopt.org.fourthSeminar.exception.Error;

public class UnauthorizedException extends SoptException {
    public UnauthorizedException(Error error, String message) {
        super(error, message);
    }
}
