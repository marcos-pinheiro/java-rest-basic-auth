# Web service RESTful com autenticação HTTP Basic utilizando JAX-RS

Criar uma autenticação baseada em HTTP Basic é uma tarefa relativamente pouco complexa, porém além de configurarmos nosso projeto, através de código ou arquivo XML por exemplo, requer que configuremos nosso servidor(Jetty, Tomcat, Glassfish, Wildfly, etc...) para aceitar tal tipo de requisição autenticada. A boa notícia é que tal tipo de configuração é relativamente simples mesmo para quem não tem o costume de "fuçar" os arquivos ou o console de seu servidor.

A autenticação <i>Basic</i> consiste em um <i>Header</i> HTTP com o nome <i>Authorization</i> e com o valor literal de Basic + usuario:senha codificados em Base64. Lembrando que usuário e senha são os mesmos configurados no realm em seu servidor.

Por exemplo, para um usuário "admin" com senha "java" o <i>header</i> HTTP ficaria <code>Authorization: Basic YWRtaW46amF2YQ==</code>

Para criar o Web service RESTful utilizei a especificação JAX-RS;

Neste exemplo eu utilizei o <a href="https://glassfish.java.net/" alt="Glassfish" title="Glassfish">Glassfish</a> na versão 4.1 como servidor para criar o exemplo.

Para criar um usuário no Glassfish acessei o console web e fui em: Configurations -> server-config -> Security -> Realms -> file

Neste criei um usuário "admin" com senha "java" e com o realm padrão "file".

Para configurar a aplicação foi necessário modificar o arquivo web.xml e sun-web.xml

<b>OBS</b>: Este é somente um exemplo de como criar um <i>web service</i> restful com autenticação HTTP <i>Basic</i>. Questões como HATEOAS, validações, uso dos <i>Status Code</i> e outras boas pŕaticas estão a parte deste exemplo.
Tentarei usar este mesmo projeto para ir adicionando estes quesitos :)
