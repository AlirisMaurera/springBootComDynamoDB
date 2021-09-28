## Projeto de cadastro de uma empresa com Spring boot e Dynamodb da AWS

Este foi um projeto feito Para o bootcamp da GFT de Java com AWS, seguindo vários tutoriais pra conectar o projeto com dynamodb da AWS. Aqui neste artigo explicarei o passo a passo para configurar o projeto com AWS.



Pré-requisitos:

- Uma conta na AWS
- Intellij
- Java 11
- Spring Boot



## 1. Criar uma tabela na aws



No buscador procurar DynamoDB

![image-20210928161011722](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928161011722.png)



Criar uma Tabela com o nome que será utilizada no projeto

![image-20210928161344204](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928161344204.png)



Dar nome da tabela, indicar a chave primaria

![image-20210928161603056](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928161603056.png)



Devemos criar um grupo e um usuário para obter as credencias pra conectar nosso projeto, no buscador procuramos por IAM

![image-20210928161929213](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928161929213.png)



![image-20210928162141851](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928162141851.png)

Dá um nome ao grupo e se atribui a permissão AmazonDynamoDBFullAccess e cria o grupo

![image-20210928162524710](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928162524710.png)



logo atribuir um usuário para o grupo criado

![image-20210928162903383](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928162903383.png)



Para este usuário se seleciona chave de acesso: acesso programático

![image-20210928163208111](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928163208111.png)

Seleciona o grupo criado, proximo: tags, depois próximo: revisar

![image-20210928163418038](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928163418038.png)

![image-20210928163700724](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928163700724.png)



Assim criamos nosso usuário só terminamos com fazer download das senhas que são como vamos a conectar o projeto à nuvem

![image-20210928164030935](C:\Users\ti\AppData\Roaming\Typora\typora-user-images\image-20210928164030935.png)





## 2. Configurando o projeto



#### Crie um projeto no Spring Boot colocar no pom.xml as seguinte dependências



```
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.data</groupId>
   <artifactId>spring-data-jpa</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-test</artifactId>
   <version>2.5.3</version>
   <scope>test</scope>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-autoconfigure</artifactId>
</dependency>
<dependency>
   <groupId>org.springframework.data</groupId>
   <artifactId>spring-data-commons</artifactId>
   <version>2.5.4</version>
</dependency>
<dependency>
   <groupId>com.amazonaws</groupId>
   <artifactId>aws-java-sdk-dynamodb</artifactId>
   <version>1.11.831</version>
</dependency>
<dependency>
   <groupId>com.github.derjust</groupId>
   <artifactId>spring-data-dynamodb</artifactId>
   <version>5.1.0</version>
</dependency>
<dependency>
   <groupId>org.projectlombok</groupId>
   <artifactId>lombok</artifactId>
   <version>1.18.20</version>
   <scope>provided</scope>
</dependency>
```



#### No application.properties

```
amazon.dynamodb.endpoint=dynamodb.us-east-1.amazonaws.com
amazon.aws.accessKey= "Colocar suas senhas descarregada no arquivo"
amazon.aws.secretkey="Colocar suas senhas descarregada no arquivo"
amazon.aws.region=us-east-1
```



#### Criar uma pasta configuration e criar uma classe DynamoDBConfig

```
@Configuration
@EnableDynamoDBRepositories(basePackages =
        "br.com.alirismaurera.conectandoJavaComDynamodbAws.repository")
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String awsEndpoint;
    @Value("${amazon.aws.accesskey}")
    private String awsAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String awsSecretKey;
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Bean
    public DynamoDBMapperConfig dynamoDBMapperConfig() {

        return DynamoDBMapperConfig.DEFAULT;
    }
    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(awsEndpoint, awsRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();
    }
}
```



E assim seu projeto já tem a conexão com DynamoDB Agora é seguir construindo o projeto com as bean correspondentes para conectar com a tabela criada na AWS.