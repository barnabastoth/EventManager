package application.Utils;

import application.model.Account.Account;
import spark.ModelAndView;
import spark.Request;

import java.util.Map;

public class ViewUtil {

    public String render(Request request, Map<String, Object> model, String view, Account account) {

        return new ThymeleafTemplateEngine().render(new ModelAndView(model, view));
    }
}
