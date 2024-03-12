# Projeto FiapLanches
teste

Este é o repositório do Projeto FiapLanches, uma aplicação incrível desenvolvida pela equipe Grupo 48. O projeto consiste em uma aplicação web que fornece serviços relacionados a uma lanchonete que está informatizando seus processos.

## Instruções de Instalação

Para executar o projeto localmente, siga as instruções abaixo:

1. Clone este repositório em sua máquina local:

   ```shell
   git clone https://github.com/raulvitoriano/fiaplanches.git
   ```

2. Clone o repositorio do webHook na sua maquina local no mesmo diretorio que clonou o repo do projeto fiaplanches:

   ```shell
   git clone https://github.com/DaniTavaresSantos/webhook-fiaplanches.git
   ```   

3. Navegue para dentro da pasta do projeto do fiaplanches para onde está os arquivos yaml do K8s referentes ao Postgress :

   ```shell
   cd fiaplanches/K8sPostgres
   ```

4. No terminal rode os seguintes comandos:

  ```shell
   kubectl apply -f persistence-volume.yaml
   ```
  ```shell
   kubectl apply -f persistence-volume-claim.yaml
   ```
  ```shell
   kubectl apply -f postgres-config-maps.yaml
   ```
   ```shell
   kubectl apply -f postgres-service.yaml
   ```
   ```shell
   kubectl apply -f postgres-service-node-port.yaml
   ```
   ```shell
   kubectl apply -f postgres-deployment.yaml
   ```

5. Navegue para dentro da pasta do projeto do webHook :

   ```shell
   cd ..
   ```
   
   ```shell
   cd ..
   ```
   ```shell
   cd webhook-fiaplanches/k8s
   ```

6. No terminal rode os seguintes comandos:

  ```shell
   kubectl apply -f k8s-webhook-config-maps.yaml
   ```
  ```shell
   kubectl apply -f k8s-webhook-clusterip-service.yaml
   ```
  ```shell
   kubectl apply -f k8s-webhook-deployment.yaml
   ```

7. Acesse o diretório do projeto fiaplanches:

   ```shell
   cd ..
   ```
   
   ```shell
   cd ..
   ```

   ```shell
   cd fiaplanches/k8s
   ```

8. No terminal rode os seguintes comandos:

  ```shell
   kubectl apply -f k8s-fiaplanches-config-maps.yaml
   ```
  ```shell
   kubectl apply -f k8s-fiaplanches-clusterip-service.yaml
   ```
  ```shell
   kubectl apply -f k8s-loadbalance-service.yaml
   ```
  ```shell
   kubectl apply -f k8s-fiaplanches-deployment.yaml
   ```

Isso iniciará os containers necessários para executar a aplicação.

## Para testar a funcionalidade da aplicação, basta utilizar a collection do Postman localizada na pasta: Collection-Postman, na raíz do projeto.

## Ordem do caminho feliz a partir dos endpoints do postman:

## Utilizar os seguintes requests, onde o body informado ja é funcional.
1. Cliente/Criar Cliente
2. Pedido/Adiciona Pedido Carrinho
3. Pedido/Paga Pedido
4. Pedido/Consulta/Consulta Pedidos Ordenados
5. Pedido/Atualiza Status Pedido

Observações:
   1. Caso você opte por deletar um cliente, todos os seus pedidos também serão deletados.
   2. O Endpoint de Atualiza Status Pedido, passa o Pedido para o proximo Status, onde não é possivel voltar o status, somente ir para o próximo. A ordem dos status da aplicação é: NO_CARRINHO, PAGAMENTO_PENDENTE, RECEBIDO, EM_PREPARO, PRONTO, FINALIZADO.
   3. Devido a possuírmos dois status de transição do pedido: NO_CARRINHO, PAGAMENTO_PENDENTE, caso o pedido se encontre em algum desses status, o mesmo não aparecerá no endpoint de Consulta Pedidos Ordenados. Para visualiza-lo neste endpoint, basta passar o pedido para um dos Status a seguir: RECEBIDO, EM_PREPARO, PRONTO, FINALIZADO.
   4. O endpoint de Paga Pedido, é o endpoint que se comunica com a aplicação do webhook, onde a aplicação principal é responsável por atualizar o status do Pedido, e o webhook simula a aprovação de um pagamento. Uma vez que o webhook possui o status de 'Aprovado' ou 'Não Aprovado', ele envia para um endpoint da aplicação fiapLanches, um request com esta informação, onde a app. fiaplanches atualiza o status do Pedido, por meio do campo isApproved, atualizando seu valor para true.
   5. É importante ressaltar que há uma possibilidade de 5% de o pagamento não ser aprovado, esta regra está localizada no webhook e é apenas uma simulação de instabilidade do sistema. Se você fizer uma nova requisição, a aplicação funcionará normalmente.

## Demonstração de Possíveis Erros

Neste projeto, incluímos uma pasta chamada `PrintScreens` na raiz do projeto. Essa pasta contém screenshots que demonstram possíveis erros que podem ocorrer durante a execução da aplicação.

É importante ressaltar que esses erros ocorrem apenas em aproximadamente 5% das vezes e são simulados para simular a instabilidade do sistema. Se você fizer uma nova requisição, a aplicação funcionará normalmente.

Se você encontrar algum desses erros durante a execução da aplicação, não se preocupe, é apenas uma simulação e não indica um problema real.

## Debitos Técnicos

Devido a estarmos com o desenvolvimento do sistema em andamento, possuímos alguns debitos técnicos ja identificados, sendo eles:
- Caso o Cliente possua um Pedido Em Andamento, não é possivel excluir este cliente da Base, onde devidoa a isto, estoura uma exceção no Response do endpoint de Delete do Cliente: cliente/{cpf}, uma imagem demonstrando o debito tecnico em questão está anexada a pasta PrintScreens na raiz do Repositorio.

teste teste
