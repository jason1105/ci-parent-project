pipeline {
    agent { docker {  image 'maven:3.8.5-openjdk-18-slim' } }
    stages {
        stage('build') {
            steps {
                sh '''
                    echo "==== Building ===="
                    ls -lah
                '''
            }
            steps {
                sh 'mvn --version'
            }
            steps {
                sh 'echo "==== Build complete ===="'
            }
        }
    }
}