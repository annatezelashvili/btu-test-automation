pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Run Maven Project') {
          steps {
            bat 'mvn clean install'
          }
        }

        stage('Get maven version') {
          steps {
            bat 'mvn --version'
          }
        }

      }
    }

  }
}
