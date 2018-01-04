package application.utils;

public class Path {
    public static class Web {
        public static final String INDEX = "/";
        public static final String LOGIN = "/login";
        public static final String REGISTRATION = "/registration";
        public static final String PROFILE = "/profile/{id}";
        public static final String MENU = "/{route}";
        public static final String EVENT_BY_ID = "/event/{id}";
        public static final String NEW_EVENT = "/event/new";
        public static final String EDIT_EVENT = "/event/{id}/edit";
        public static final String ADD_EVENT_SPEAKERS = "/event/{id}/speakers/add";
    }


    public static class Api {
        public static final String EVENT_BY_ID = "/api/event/{id}";
    }

    public static class Template {
        public static final String INDEX = "index";
    }

    public static class Fragment {
        public static final String EVENT = "fragment/content/event";
        public static final String EDIT_EVENT = "fragment/content/edit_event";
        public static final String NEW_EVENT = "fragment/content/new_event";
        public static final String PROFILE = "fragment/content/userProfile";
        public static final String MENU = "fragment/content/menu";
        public static final String ADD_EVENT_SPEAKERS = "fragment/content/add_speakers";
    }

}
