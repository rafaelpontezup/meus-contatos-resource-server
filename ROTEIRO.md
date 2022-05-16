# ROTEIRO

## Configurando OAuth2 Resource Server

1. Introdução sobre o projeto: **Meus Contatos**;
2. Conhecendo a API REST no Insomnia;
3. Configurando Maven:
```xml
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>
```
4. Abrir documentação do Spring Security: https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html ; 
5. Configurando `application.properties`:
```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://meu-keycloack.local/auth/realms/meus-contatos
```
5. Configurando `Access Rules`:
```java
@Configuration
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers(HttpMethod.GET, "/api/contatos").hasAuthority("SCOPE_contatos:read")
              .antMatchers(HttpMethod.GET, "/api/contatos/**").hasAuthority("SCOPE_contatos:read")
              .antMatchers(HttpMethod.POST, "/api/contatos").hasAuthority("SCOPE_contatos:write")
              .anyRequest()
                .hasAuthority("SCOPE_contatos:write")
              .and()
                 .oauth2ResourceServer()
                   .jwt();
      ;
   }
}
```
6. Configurando Spring Expression Language (SpEL):
```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
   // ...
}
```
7. Tentando acessar a aplicação novamente pelo Insomnia: **recebe 401**
8. Configurando o Keycloak
   1. Rodando localmente via Docker-Compose: `docker-compose-keycloak.yml`
   2. Acessando Admin Console: http://localhost:18080/auth/admin/ ;
   3. Criando Realm `meus-contatos`;
   4. Criando User `rafael.ponte`;
   5. Criando Client `meus-contatos-client`:
      1. Access Type: `Confidential`;
      2. Redirect URL: `*`;
   6. Criando Scopes `contatos:read` e `contatos:write`;
   7. Adicionando Scopes ao Client;
9. Configurando e testando Insomnia com OAuth2:
   1. Configurando endpoint "Novo contato": Authorization Code Flow;
   2. Configurando endpoint "Detalha contato": Resource Owner Password Flow;
10. **Anonymous**: detalhando um contato e mostra o problema do `criadoPor`;
11. Extraindo o usuário do access token ao criar Novo contato:
```java
// injeta como parametro de método do controller
@AuthenticationPrincipal Jwt user

// extrai username do token
String usuario = user.getClaim("preferred_username")

// passa como parametro pro toModel()
Contato contato = request.toModel(usuario);
```
12. Reinicia aplicação;
13. Cadastra novo contato;
14. Detalha contato cadastrado e exibe campo `criadoPor`;
15. Finaliza;
