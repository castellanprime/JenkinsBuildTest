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
      	sh "cd /JenkinsBuildTest/"
        sh "mvn clean install"
        sh "mvn package"
      }
    }
    stage('archive') {
      steps {
        archiveArtifacts 'target/*.jar'
      }
    }
  }
}