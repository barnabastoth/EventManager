package application.utils;

public class Path {
    public static class Web {
        public static final String INDEX = "/";
        public static final String LOGIN = "/login";
        public static final String REGISTRATION = "/registration";
        public static final String PROFILE = "/profile/{id}";
        public static final String EVENT_BY_ID = "/event/{id}";
        public static final String NEW_EVENT = "/event/new";
        public static final String EDIT_EVENT = "/event/{id}/edit";
        public static final String MENU = "/{route}";
    }


    public static class Api {
        public static final String LOGIN = "/api/login";
        public static final String REGISTRATION = "/api/registration";
        public static final String MY_PROFILE = "/api/profile";
        public static final String EVENT_BY_ID = "/api/event/{id}";
        public static final String LATEST_EVENT = "/api/event/latest";
        public static final String NEW_EVENT = "/api/event/new";
        public static final String EDIT_EVENT = "/api/event/{id}/edit";
        public static final String MENU = "/api/{route}";
        public static final String NEW_EVENT_COMMENT= "/api/event/{id}/comment/new";
    }

    public static class Template {
        public static final String INDEX = "index";
    }

    public static class Fragment {
        public static final String EVENT = "fragment/content/event";
        public static final String ALL_EVENTS = "fragment/content/all-events";
        public static final String EVENT_MANAGER = "fragment/content/edit_event";
        public static final String EVENT_COMMENT = "fragment/content/event-comment";
        public static final String PROFILE = "fragment/content/user-profile";
        public static final String EDIT_PROFILE = "fragment/content/edit-profile";
        public static final String MENU = "fragment/content/menu";
    }

}
