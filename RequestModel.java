public class RequestModel {

    static public int EXIT_REQUEST = 0;
    static public int CUSTOMER_REQUEST = 1;
    static public int SAVE_ORDER_REQUEST = 2;
    static public int LOAD_ORDER_REQUEST = 22;
    static public int LOAD_PRODUCT_REQUEST = 3;
    static public int SAVE_PRODUCT_REQUEST = 33;
    static public int USER_REQUEST = 4;


    public int code;
    public String body;
}
