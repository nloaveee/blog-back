package study.blogback.common;

public interface ResponseMessage {

    // HTTP Status = 200
    String SUCCESS = "Success";

    // HTTP Status = 400
    String VALIDATION_FAILED = "Validation Failed";
    String DUPLICATE_EMAIL = "Duplicate email";
    String DUPLICATE_NICKNAME = "Duplicate nickname";
    String DUPLICATE_ID = "Duplicate id";
    String DUPLICATE_TEL_NUMBER = "Duplicate tel number";
    String NOT_EXISTED_USER ="This user does not exist";
    String NOT_EXISTED_BOARD ="This board does not exist";

    //HTTP Status = 401
    String SIGN_IN_FAIL="Login information mismatch";
    String CERTIFICATION_FAIL ="Certificate information mismatch";
    String AUTHORIZATION = "Authorization Failed";

    // HTTP Status = 403
    String NO_PERMISSION = "Do not have permission";

    // HTTP Status = 500
    String MAIL_FAIL = "Mail send failed";
    String DATABASE_ERROR = "Database error";
}
