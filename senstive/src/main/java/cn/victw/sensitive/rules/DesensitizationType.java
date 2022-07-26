package cn.victw.sensitive.rules;


import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vincent Tao
 * @date 2021/12/2 13:18
 */
public enum DesensitizationType {

    // 手机号
    PHONE(s -> s.replaceAll("(?<=\\d{3})\\d(?=\\d{4})", "*")),

    // 身份证\社保卡\微qq
    ACCOUNT_BAK(s -> s.replaceAll(".(?=.{4})", "*")),
    ACCOUNT(s-> {
        if (null == s || "".equals(s)) {
            return s;
        }
        // 开头保留个数 ,结尾保留个数
        int start = 0, end = 1;
        if (s.length() >= 18) {
            start = 4;
            end = 4;
        } else if (s.length()>= 8) {
            start = 2;
            end = 3;
        } else if (s.length() >3){
            end = 2;
        }
        String pattern = String.format("(?<=\\w{%d})\\w(?=\\w{%d})", start, end);
        return s.replaceAll(pattern, "*");
    }),

    // 邮箱
    MAIL(s -> s.replaceAll("(.)*(?=@)", "****")),

    // 姓名
    USER_NAME(s -> s.replaceAll(".(?=[\u4e00-\u9fa5]{1})", "*")),

    // 地址
    ADDRESS(s -> {
        if (null == s || s.length() == 0) {
            return s;
        }
        int truncate = s.length() > 12 ? 6 : (s.length() / 2);
        String pattern = "(?<=.{" + truncate + "}).";
        return s.replaceAll(pattern, "*");
    }),

    /**
     *  统一登录门户特殊情况, 数据格式为  [ 证件号1(证件号2) ]
     */

    DOUBLE_ACCOUNT(s -> {
        Pattern pattern = Pattern.compile("(.+)\\((.+)\\)");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            return ACCOUNT.desensitizer.apply(s);
        }
        return s.replaceAll("(.+)\\((.+)\\)", ACCOUNT.desensitizer.apply("$1") + "(" + ACCOUNT.desensitizer.apply("$2") + ")" );
    }),

    DOUBLE_PHONE(s -> {
        Pattern pattern = Pattern.compile("(.+)\\((.+)\\)");
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            return PHONE.desensitizer.apply(s);
        }
        return s.replaceAll("(.+)\\((.+)\\)", PHONE.desensitizer.apply("$1") + "(" + PHONE.desensitizer.apply("$2") + ")" );
    }),

    OTHER(s -> s);

    private final Function<String, String> desensitizer;

    DesensitizationType(Function<String, String> desensitizer) {
        this.desensitizer = desensitizer;
    }

    public Function<String, String> desensitizer() {
        return desensitizer;
    }

}
