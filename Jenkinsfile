pipeline{
    agent any
    stages{
        stage('Build project jar'){
            steps{
                bat 'mvn clean package -DskipTests'
            }
         }
         stage('Build API app docker image'){
                 steps{
                     bat "docker build -t=samirshh9/apiapp-docker -f Dockerfile.local ."
             }
          }
        stage("Build rest assured docker image"){
            steps{
                bat "docker build -t=samirshh9/restassured-docker ."
            }

        }
        stage("push API APP and RestAssured images to docker hub"){
            steps{
            withCredentials([usernamePassword(credentialsId: 'DH', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                bat "docker login --username=${USERNAME} --password=${PASSWORD}"
                bat "docker push samirshh9/apiapp-docker:latest"
                bat "docker push samirshh9/restassured-docker:latest"
            }
          }

        }

    }
}
