package application.utils;

public class Path {

    public static class Web {
        public static final String INDEX = "/";
        public static final String EVENT_BY_ID = "/event/{id}";
        public static final String MENU_BY_ID = "/menu/{id}";
        public static final String LOGOUT = "/logout";
        public static final String LOGIN = "/login";
        public static final String REGISTRATION = "/registration";
        public static final String PROFILE = "/profile/{id}";
        public static final String MENU = "/{route}";
    }

    public static class Api {
        public static final String EVENT_BY_ID = "/api/event/{id}";
    }

    public static class Template {
        public static final String INDEX = "index";
        public static final String PROFILE = "profile";
        public static final String MENU = "menu";
    }

}
