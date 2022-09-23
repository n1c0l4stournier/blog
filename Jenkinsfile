pipeline {

    agent {
        kubernetes {
            idleMinutes 5  // how long the pod will live after no jobs have run on it
            yamlFile 'pod.yaml'
            defaultContainer 'maven'
        }
    }

    stages {
        stage('Build') {
            steps {
                withCredentials([file(credentialsId: 'NEXUS_SETTINGS', variable: 'NEXUS_SETTINGS')]) {
                    sh 'mvn clean package -DskipTests -s $NEXUS_SETTINGS'
                }
            }
        }
        stage('Test') {
            steps {
                withCredentials([file(credentialsId: 'NEXUS_SETTINGS', variable: 'NEXUS_SETTINGS')]) {
                    sh 'mvn clean test -s $NEXUS_SETTINGS'
                }
            }
        }
        stage('Deploy') {
            steps {
                withCredentials([file(credentialsId: 'NEXUS_SETTINGS', variable: 'NEXUS_SETTINGS')]) {
                    sh 'mvn clean deploy -DskipTests -s $NEXUS_SETTINGS'
                }
            }
        }
    }

}