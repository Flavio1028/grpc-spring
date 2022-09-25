## Exemplo de uso do gRPC em um projeto Spring Boot com Java

O Spring não oferece suporte nativo ao gRPC, portanto, neste projeto foi usado a lib [gRPC-Spring-Boot-Starter](https://yidongnan.github.io/grpc-spring-boot-starter/en/).


# Stack:

- Java 11
- Spring Boot
- gRPC
- Assertj
- Mockito

# Recursos utilizados:
- GrpcExceptionHandler
- Testes integrados dos enpoints gRPC
- Testes unitários

# Como executar:
Antes de mais nada você deve executar o Protobuf para que sejam gerados os stubs a partir do arquivo .proto do projeto. Para isso, siga esses passos:
- 1º Na lateral esquerda do Intellij, acesse a aba Maven e em plugins navegue até o **protobuf** e execute **protobuf:compile** e **protobuf:compile-custom**.
- 2º Com os stubs gerados, você pode executar todos os testes da aplicação para ver o seu funcionamento ou até mesmo executar a aplicação e testar as chamadas usando um client gRPC como o BloomRPC ou Insomnia.

## License
[MIT](https://choosealicense.com/licenses/mit/)