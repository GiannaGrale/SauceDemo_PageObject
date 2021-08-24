package endpoints;

public interface ReqresEndpoints {

    String ALl_USERS = "/api/users?page=%d";
    String ONE_USER = "/api/users/%d";
    String RESOURCES = "/api/unknown";
    String SINGLE_RESOURCES = "/api/unknown/%d";
    String DELAYED_USER = "/api/users?delay=%d";
    String USERS = "/api/users";
    String REGISTER_USER = "api/register";
    String LOGIN_USER =  "/api/login";


}
