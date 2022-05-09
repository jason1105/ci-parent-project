pipeline {
    agent { 
        docker {  
            image 'maven:3.8.5-openjdk-18-slim' 
            // $HOME is jenkins home.  i.e. /var/jenkins_home
            args '-v $HOME/.m2:/root/.m2'
            reuseNode true
        } 
    }
    stages {
        stage('build') {
            steps {
                // $HOME is os user home. i.e. /root
                sh 'echo $HOME'
                sh '''
                    echo "==== Building ===="
                    ls -lah
                '''

                retry(3) {
                    sh 'mvn --version'
                    sh 'pwd'
                }

                sh 'mvn clean compile package'
                sh 'cp -r ./target/ci-parent-project-1.0-SNAPSHOT.jar $HOME/target/'

                timeout(time: 3, unit: 'MINUTES') {
                    retry(3) {
                        sh 'echo "==== Build complete ===="'
                    }
                }

            }
        }
    }
    post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}