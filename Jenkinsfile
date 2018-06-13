pipeline {
  agent any
  stages {
    stage('download') {
      steps {
        git "https://github.com/castellanprime/JenkinsBuildTest.git"
      }
    }
    stage('build') {
      steps {
        sh "mvn clean package"
      }
    }
    stage('archive') {
      steps {
        archiveArtifacts 'target/*.jar'
      }
    }
  }
}