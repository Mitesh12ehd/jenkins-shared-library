#!/user/bin/env groovy

def call(){
    echo "building the application with branch $BRNACH_NAME"
    sh "mvn package"
}