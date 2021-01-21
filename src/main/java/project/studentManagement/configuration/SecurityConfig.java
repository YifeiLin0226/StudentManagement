package project.studentManagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/*
This class is for setting up the security and logging for the website
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // this data source is from application properties file
    @Autowired
    private DataSource securityDataSource;

    // retrieve the authentication information from database (username, password, role)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource);


    }

    // set up the authorization for different roles
    // set up the login page
    // set up the page for accessing the unauthorized page
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                .antMatchers("/students/list").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                .antMatchers("/students/**").hasRole("ADMIN")
                .antMatchers("/studentCourses/**").hasRole("STUDENT")
                .antMatchers("/instructors/list").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                .antMatchers("/instructors/**").hasRole("ADMIN")
                .antMatchers("/instructorCourses/**").hasRole("INSTRUCTOR")
                .antMatchers("/courses/list").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                .antMatchers("/courses/details").hasAnyRole("ADMIN","STUDENT","INSTRUCTOR")
                .antMatchers("/courses/enroll").hasRole("STUDENT")
                .antMatchers("/courses/details/enroll").hasRole("STUDENT")
                .antMatchers("/courses/**").hasRole("ADMIN")
                .antMatchers("/courses/details/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/showMyLoginPage").permitAll()
                .loginProcessingUrl("/authenticateTheUser").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied");
    }


}
