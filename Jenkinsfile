pipeline {
    agent { docker {  image 'maven:3.8.5-openjdk-18-slim' } }
    stages {
        stage('build') {
            steps {
                sh '''
                    echo "==== Building ===="
                    ls -lah
                '''

                sh 'mvn --version'

                retry(3) {
                    sh 'mvn clean compile package'
                }

                sh 'cp target/ $HOME/target/'

                sh 'echo "==== Build complete ===="'
            }
        }
    }
}