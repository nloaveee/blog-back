package study.blogback.common;


public interface ResponseCode {
    // HTTP Status = 200
    String SUCCESS = "SU";

    // HTTP Status = 400
    String VALIDATION_FAILED = "VF";
    String DUPLICATE_EMAIL = "DE";
    String DUPLICATE_ID = "DI";
    String DUPLICATE_NICKNAME = "DN";
    String DUPLICATE_TEL_NUMBER = "DT";
    String NOT_EXISTED_USER ="NU";
    String NOT_EXISTED_BOARD ="NB";

    //HTTP Status = 401
    String SIGN_IN_FAIL="SF";
    String CERTIFICATION_FAIL="CF";
    String AUTHORIZATION = "AF";

    // HTTP Status = 403
    String NO_PERMISSION = "NP";

    // HTTP Status = 500
    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";
}
