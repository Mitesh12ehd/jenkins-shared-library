#!/user/bin/env groovy

package com.example

// implement Serializable interface
// Purpose of Serializable to make all the thing (environment variable, shell executor, withCredential)

class Docker implements Serializable {

    def script

    // script contain all the command and method for pipeline
    Docker(script){
        this.script = script
    }

    def buildDockerImage(String imageName){
        script.echo "building the docker image..."
        script.withCredentials([
                script.usernamePassword(
                        credentialsId: 'docker-hub-repo',
                        usernameVariable: 'USER',
                        passwordVariable: 'PASS'
                )
        ]){
            // inside this block we have access to USER and PASS variable

            // build image
            script.sh "docker build -t $imageName ."

            // login into docker, taking password from input as it become masked
            // use variable in single qualities and inside bracket
            // script.sh "echo ${PASS} | docker login -u ${USER} --password-stdin"
            script.sh "echo '${script.PASS}' | docker login -u '${script.USER}' --password-stdin"

            // push image
            script.sh "docker push $imageName"
        }
    }
}
