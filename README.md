# poc-on-demand-instance-with-kotlin

# Objetivo 

Apresentar duas abordagens de instanciação de classes por referência. 

1ª opção: Utilizando o próprio framework do Spring para gerar instâncias prévias na inicialização do aplicativo.

2ª Opção: Utilizando a instância por demanda, dessa forma as classes só são instanciadas quando necessário. 

# Ideia central

A aplicação é um serviço de validações simples, como verificar se o usuário possui conta bancária, se usuário possui cpf, conta bancároa, etc.. 
Um endpoint é disponibilizado para que o usuário informe um identificador da pessoa que ele queira validar, e qual o perfil dele (perfil cadastrado no banco com as validações atreladas a ele, falaremos mais adiante)
No final do processamento informamos se a validação foi concluída, e se o campo validado não tive sido encontrado geramos uma msg com esse retorno. 

Endpoint para requisição: 

```
@RestController
@RequestMapping("/validations")
class UserValidationsController(private val validationService: ValidationService) {
    @PostMapping("/user")
    fun validateUser(@RequestParam ucode: String, @RequestParam businessProfile: String): ValidationResponse {
        return validationService.process(ucode, businessProfile)
    }
}
```

Retorno da validação
```
{
    "validationStatus": "CONCLUIDO",
    "createDate": "2023-11-22T14:46:49.174196",
    "dataNotFound": [
        "Conta bancária não encontrado"
    ]
}
```

### Mapeamento da tabela de perfil

Um perfil possui um nome de perfil, e referência de classes de validações. 

![Captura de Tela 2023-11-23 às 08 50 54](https://github.com/gmcarvalho/poc-on-demand-instance-with-kotlin/assets/33256112/4b5b6063-5e82-4a81-b04a-300efe68deba)

O mapeamento foi meramente ilustrativo, com apenas uma tabela. Salvamos um perfil e uma referência de classe atrelada a ela nessa tabela.
Foi utilizado aqui um banco mysql local, mas você poderá usar um banco H2, ou outro de sua preferência.
Essa tabela por ora apresenta só isso, mas a intenção é criarmos um relacionamento many to many, para que um perfil abranja mais referências de classes. 

Exemplo: 
Para o perfil: `UserMeiValidation`, queremos que sejam validados as classes `CpfValdiation` e `BirthdateValidation`

Para outro perfil: `BankCTPSProfile`, queremos que sejam validados as classes `CpfValdiation` e `BankAccountValidation` 

Para isso o banco de dados precisa comportar que um perfil esteja atrelado a várias validações. Mas imagine que para esse exemplo, o relacionamento da tabela não é o objetivo principal. 

### Modelo das classes de validações 

Cada classe de validação tem um repsonsabilidade única, que é, validar alguma informação que o usuário possui. Para isso criamos uma classe abstrata abaixo,

```
abstract class UserValidationAbstract {

    open fun verify(userDTO: UserDTO, validationResponse: ValidationResponse) {
        if (validate(userDTO, validationResponse))
            onSuccess(userDTO, validationResponse)
        else onFailure(userDTO, validationResponse)
    }

    protected abstract fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean

    protected abstract fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse)

    protected abstract fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse)
}
```

E classes de validações nesse modelo: 

```
@Component
@ValidationIdentifier("bank-account-validation")
class BankAccountValidation: UserValidationAbstract() {
    override fun validate(userDTO: UserDTO, validationResponse: ValidationResponse): Boolean {
        return userDTO.bankAccounts.isNotBlank()
    }

    override fun onSuccess(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Conta bancária encontrada. ")
    }

    override fun onFailure(userDTO: UserDTO, validationResponse: ValidationResponse) {
        println("Conta bancária não foi encontrada. ")
        validationResponse.dataNotFound.add(BANK_ACCOUNT_NOT_FOUND.msg)
    }
}
```

Essa classe de validação, implementa os métodos dessa classe abstrata, e o objetivo dela é validar se o usuário possui conta bancária, se não, apenas é adicionado em uma lista que esse dado não foi encontrado. 
Essa informação é que vai ser lida pelo usuário que está fazendo a solicitação de validação. 

No aplicativo temos outras classes: 

<img width="288" alt="Captura de Tela 2023-11-23 às 09 09 00" src="https://github.com/gmcarvalho/poc-on-demand-instance-with-kotlin/assets/33256112/c99623c9-ab44-431c-812b-2289790a2568">

Para cada classe criamos um identificador, esse identificador será salvo no banco de dados, atrelado ao perfil, que, foi visto nos passos anteriores. 
Você poderia levar pro banco o nome da sua classe de validação, porém há um risco de alguém algum dia alterar o nome da classe porque alguma letra do nome tenha ficado errado, e isso vai causar divergência com o nome que você salvou anteriormente 
em algum perfil. Por isso é importante tentar outra maneira de fazer essa referência. Existem outras formas, mas a que usei foi simples. 

Criamos uma annotation: 

```
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ValidationIdentifier(val value: String)
```

Essa annotation recebe um valor `string`. Na classe que está no exemplo anterior perceba que anotamos abaixo do `@Component`, essa anotação que acabamos de criar: `@ValidationIdentifier("bank-account-validation")`

Dessa forma podemos atribuir uma referência que não necessariamente seja o nome da classe criada. Isso iremos salvar no banco de dados. 

### Pré inicialização

Agora que você sabe como foi construído, quando houver uma requisição no nosso endpoint, precisamos instanciar as classes. 

Para isso, vamos começar com nossa primeira opção, a pré-inicialização usando os próprios recursos do Spring. 
O Spring Framework já nos disponibiliza uma facilidade de injeções, e quando nós adicionamos a annotation `@Component` às classes, 
ao assiná-las em um contrutor do serviço, (que vamos utilizar para filtrar as classes que precisamos), automaticamente o Spring já instancia todas as classes pra nós.

Veja o exemplo abaixo: 

```
@Service
class ValidationConfigService(private val instantiatedClasses: List<UserValidationAbstract>) {

    fun loadValidationConfigForProfile(businessProfile: String, validationClassIdentifier: String): List<UserValidationAbstract> {
        val validationClasses = mutableListOf<UserValidationAbstract>()

        for (validationClass in instantiatedClasses){
            val annotation = validationClass.javaClass.getAnnotation(ValidationIdentifier::class.java)

            if (annotation != null && annotation.value == validationClassIdentifier) {
                validationClasses.add(validationClass)
            }
        }

        return validationClasses
    }
}
```

Aqui `private val instantiatedClasses: List<UserValidationAbstract>`, automaticamente quando esse serviço for chamado, vai perceber que temos 5 instâncias de classes, porque como disse o Spring já resolve essa inicialização, sem muitas burocracias. 

Ao percorrer as classes instanciadas, nós precisamos somente das classes cujo perfil lá no banco, que mostramos anteriormente, possui de referência. Então não preciso rodar todas se o perfil cadastrado só quer que validdemos conta bancária. 
Como elas estão instanciadas previamente, eu preciso eliminar o que não quero, uma forma de fazer isso, é verificar qual das classes instanciadas possui a referência que foi salva no banco. 

Com isso utilizamos essa parte: `validationClass.javaClass.getAnnotation(ValidationIdentifier::class.java)`

A cada classe é como uma pergunta: E aí?! A referência dessa classe é igual a que está salva no meu perfil lá do banco, se não for contia procurando pra nós aí. 
Assim que percorrer e achar a classe com a mesma referência no banco, adicionamos isso a uma lista, e retornamos para quem chamou. 

```
fun process(ucode: String, businessProfile: String): ValidationResponse {
    val user = findUser(ucode) //todo busca os dados no banco
    val validationClassIdentifier = businessProfileService.findValidationByBusinessProfile(businessProfile)
    val validationClasses = validationConfigV2Service.loadValidationConfigForProfile(businessProfile, validationClassIdentifier)

    val validationResponse = ValidationResponse()

    for (validation in validationClasses) {
        validation.verify(user, validationResponse)
    }

    validationResponse.validationStatus = "CONCLUIDO"
    return validationResponse
}
```

Quando você rodar a aplicação e ao adicionar um breakpoint na linha 164, mesmo que o aplicativo tenha instanciado as 5 classes, com nossa solução anterior, só 1 classe de validação vai chegar aqui pra continuar o fluxo. 
Isso porque só temos uma referência de classe salva no banco. 

A inicialização prévia é a mais utilizada.  

Uma das suas vantagens é que é fácil de utilizar já que o framework já resolve tudo que precisamos. E essa abordagem tem menor risco de overhead dinâmico (refere-se aos custos adicionais associados às operações dinâmicas que ocorrem em tempo de execução) 

Em contrapartida, dependendo da quantidade de classes que você adicionar, e da coplexidade, a inicialização prévia pode virar um gargalo, uma vez que mesmo que você não vai usar todas as classes, mesmo assim recursos de memória são alocados. 
Além disso, pode haver um tempo de inicialização maior até que todas as classes sejam instanciadas. 


### Inicialização on demand - sob demanda

Diferente da abordagem anterior, essa opção utilia menos os recursos do Spring, não anotamos as classes, e nem a assinamos no construtor do nosso método. 

Ao invés disso, buscamos as referências no banco, e instanciamos se for necessário. 

```
private fun createValidationBean(validationClassIdentifier: String):  List<UserValidationAbstract> {
    val beanType = try {
        applicationContext.getBeansWithAnnotation(ValidationIdentifier::class.java)
            .values
            .firstOrNull { it::class.java.getAnnotation(ValidationIdentifier::class.java)?.value == validationClassIdentifier }
    } catch (e: ClassNotFoundException) {
        //tratar erro 
    }
    return mutableListOf(beanType as UserValidationAbstract)
}
```

Nesse método acima nós geramos a instância apenas da classe que tive o identificador passado na assinatura, aquele atrelado ao perfil, que você já conhece dos passos anteriores. 
Nesse modelo, nenhuma classe é instanciada além da que você de fato precisa. 

A vantagem desse modelo é que economizamos recursos de memória e processamento, porque as demais classes não serão inicializadas. 
A inicialização é mais rápida, pois quanto menos classes você precisar inicializar no início, resultam em tempos melhores. 

Mas a grande desvantagem pode ser o overhead de instanciação, isso está associado à instanciação dinâmica de classes duante a execução. Isso pode ser mitigado como por exemplo com uso de cache, se você receber mais requsições por segundo, e um perfil precisar de algumas classes que outro perfil já inicializou, o cache ajudaria a manter e não repetir o processo todo. E existem outras abordagens que podem ser exploradas melhor. 

Devido a essas tratativas, pode gerar também uma complexidade de tratativas no código. Mas a escolha do modelo a seguir vai depender do seu contexto. 

Alguns pontos que podem ser levados em consideração: 

* Volume de Mensagens: Se a maioria das mensagens requer apenas algumas classes, a instanciação on demand pode ser mais eficiente
* Recursos Disponíveis: Se os recursos são limitados e a economia de memória é crucial, a instanciação on demand pode ser mais vantajosa.
* Escalabilidade: Considere como cada abordagem lida com o aumento do volume de mensagens. A instanciação on demand pode escalar melhor em certas situações.
* Manutenção do Código: Avalie a complexidade e a manutenção do código em ambas as abordagens. A instanciação on demand pode ser mais flexível, mas a inicialização prévia pode ser mais simples de gerenciar.
