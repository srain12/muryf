package xyz.ibenben.zhongdian.plan.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "plan.redirect")
@PropertySource("classpath:/planconstant.properties")
public class PlanRedirect {
    private String urlPre;

    public String getUrlPre() {
        return urlPre;
    }

    public void setUrlPre(String urlPre) {
        this.urlPre = urlPre;
    }

}
