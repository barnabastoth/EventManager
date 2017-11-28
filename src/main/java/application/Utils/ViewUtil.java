package application.Utils;

import application.model.Account.Account;
import application.model.Account.AdminAccount;
import application.model.Account.UserAccount;
import spark.ModelAndView;
import spark.Request;

import java.util.Map;

public class ViewUtil {

    public String render(Request request, Map<String, Object> model, String view, Account account) {

        String accountType;
        if(account instanceof AdminAccount) {
            accountType = "admin";
        } else if(account instanceof UserAccount) {
            accountType = "user";
        }

        model.put("currentUser", account != null ? account.getAccountName() : null);
        model.put("accountType", account != null ? account.getAccountName() : null);

        return new ThymeleafTemplateEngine().render(new ModelAndView(model, view));
    }
}
