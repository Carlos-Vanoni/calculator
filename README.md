# Aplicação

video explicando: https://youtu.be/JFUqg25FyOE
Repositorio: https://github.com/Carlos-Vanoni/calculator.git

## Instalações e Ajustes

- *Jenkins*
    1.Baixe o Jenkins from the [aqui](https://www.jenkins.io/download/)
 seguindo esses passos na [documentção](https://www.jenkins.io/doc/book/installing/)
    2. se tudo tiver certo, o Jnekins vai estar hospedado na URL `http://localhost:8080`.
    3. Ao acessar o Jenkins ele irá pedir uma password, que você consegue pelo caminho `/var/lib/jenkins/secrets/initialAdminPassword`
    4. Isntale os plugins sugeridos.
    5. É necessário baixar mais um plugin. Seguindo os passos `Manage Jenkins > Manage Plugins > Available` procure e baixe um plugin chamado `Artifactory Plugin`.

- *JFrog Artifactory*
    1. Baixe JFrog Artifactory [aqui](https://jfrog.com/download-jfrog-platform/) seguindo esses passos na [documentção](https://www.jfrog.com/confluence/display/JFROG/Installing+Artifactory)
    2.apos instalado, vá para o terminal, entre no diretório do Artifactory Jfrog e execute o comando `app/bin/artifactory.sh start`
    3. Se tudo tiver correto, o Artifactory Jfrog estará hospedado na URL `http://localhost:8082`.
    4. Agora é necessário que crie um repositório para isso, já logado, acesse `Repositories > Repositories`
    5. Aperte `Add Repositories > Local Repository`
    6. Marque a opção `Gradle`
    7. Coloque a Key como `calculator-app` e salve.
    8. Agora é necessário configurar Jenkins com JFrog Artifactory
    9. No dashboardo do Jenkins vá em  `Manage Jenkins > Configure System` procure onde está a parte de conexão com o `Jfrog`
    10. Clique em `Add JFrog Plataform Instance`
    11. Preencha o ID com `Artifactory` e coloque no acesso `http://localhost:8082`.
    12. Coloque suas credenciais do Jfrog.

- *Packer*
    1. Baixe o Packer [aqui](https://learn.hashicorp.com/tutorials/packer/get-started-install-cli)

- *Docker*
    1. Baixe o Docker seguindo esses passos [aqui](https://docs.docker.com/engine/install/).
    2. Rode o comando `sudo chmod 666 /var/run/docker.sock` no terminal para o Docker fazer comandos sem precisar do `sudo`.

- *Dockerhub*
    1. vá para o site do Dockerhub [aqui](https://hub.docker.com/signup) e se registre.
     2. No dashboard do Jenkins vá para `Manage Jenkins > Manage Credentials > (global) > Add Credentials`
     1. coloque em ID `dockerhub-credentials`
        2. Coloque no `username` o seu username do dockerhub.
        3. Coloque no `password` o seu token do dockerhub.
            1. Para pegar o token é só ir no Dockerhub token e ir em `Settings > Security > Access Tokens`.


## Job 1 - Build

- Vá para o dashboard od Jenkins, clique em `New Item`.
    1. Coloque o nome `job1`.
    2. Marque a opção `Pipeline` e clique em `ok`
    3. Mais abaixo na `Pipeline` quando aparecer `Definition` mude para `Pipeline script from SCM`
    4. Marque `Git` e coloque a seguinte URL `https://github.com/Carlos-Vanoni/calculator.git`
    5. Em `Script Path`, coloque `job1/Jenkinsfile` e salve.


## Job 2 - Provision

- Vá para o dashboard od Jenkins, clique em `New Item`.
    1. Coloque o nome `job2`.
    2. Marque a opção `Pipeline` e clique em `ok`
    3. Mais abaixo na `Pipeline` quando aparecer `Definition` mude para `Pipeline script from SCM`
    4. Marque `Git` e coloque a seguinte URL `https://github.com/Carlos-Vanoni/calculator.git`
    5. Em `Script Path`, coloque `job2/Jenkinsfile` e salve.
    6. vá novamente para o dashboard, entre no job1 e aperte `build now`


## Job 3 - Run

- Vá para o dashboard od Jenkins, clique em `New Item`.
    1. Coloque o nome `job3`.
    2. Marque a opção `Pipeline` e clique em `ok`
    3. Mais abaixo na `Pipeline` quando aparecer `Definition` mude para `Pipeline script from SCM`
    4. Marque `Git` e coloque a seguinte URL `https://github.com/Carlos-Vanoni/calculator.git`
    5. Em `Script Path`, coloque `job3/Jenkinsfile` e salve.
    6. vá novamente para o dashboard, entre no job1 e aperte `build now`
