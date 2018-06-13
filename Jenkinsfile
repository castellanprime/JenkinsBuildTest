pipeline {
  agent any
  stages {
    stage('download') {
      steps {
        sh '''git "https://github.com/castellanprime/JenkinsBuildTest.git"
'''
      }
    }
    stage('build') {
      steps {
        sh 'sh "mvn clean package"'
      }
    }
    stage('archive') {
      steps {
        archiveArtifacts 'target/*.jar'
      }
    }
  }
}