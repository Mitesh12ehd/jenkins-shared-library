#!/user/bin/env groovy

def call(){
    echo "building the application with branch $BRANCH_NAME"
    sh "mvn package"
}