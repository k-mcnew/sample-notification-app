import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * The entry point for this application
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMongoRepositories
@EnableAsync
public class Application {

    /** The outbound port to send notifications to */
    @Value("${mail.smtp.port}")
    private int mailSmtpPort;

    /**
     * Bean configuration for the mail sending class
     * @return a new instance of a MailSender
     */
    @Bean
    public MailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setPort(mailSmtpPort);
        return mailSender;
    }

    /**
     * The entry point for this application
     * @param args Collection of arguments to pass into the application
     * @throws Exception if errors while running the application
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
