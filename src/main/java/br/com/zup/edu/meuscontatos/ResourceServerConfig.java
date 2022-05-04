package br.com.zup.edu.meuscontatos;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/contatos/**").hasAuthority("SCOPE_contatos:read")
            .antMatchers(HttpMethod.POST, "/api/contatos").hasAuthority("SCOPE_contatos:write")
            .antMatchers(HttpMethod.PUT, "/api/contatos/**").hasAuthority("SCOPE_contatos:write")
            .antMatchers(HttpMethod.DELETE, "/api/contatos/**").hasAuthority("SCOPE_contatos:write")
            .antMatchers(HttpMethod.POST, "/api/contatos/*/telefones").hasAuthority("SCOPE_contatos:write")
            .antMatchers(HttpMethod.DELETE, "/api/contatos/*/telefones/**").hasAuthority("SCOPE_contatos:write")
            .anyRequest().authenticated()
            .and()
                .oauth2ResourceServer()
                    .jwt(); // atencao: necessario pois estamos sobrescrevendo a conf do .properties
        ;
    }

}
