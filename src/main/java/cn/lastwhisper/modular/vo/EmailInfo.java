package cn.lastwhisper.modular.vo;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value={"classpath:email.properties"})
public class EmailInfo {


    @Value(value="${email.username}")
    private String  userName ;

    @Value(value="${email.password}")
    private String  password ;

    @Value(value="${email.default.to}")
    private String  to;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getTo() {
        return to;
    }
}
