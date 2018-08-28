
pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
            }
        }
    }
	post {
        always {
	    /* Use slackNotifier.groovy from shared library and provide current build result as parameter */   
            slackSend (color: '#FFFF00', message: "STARTED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL}), ${GIT_COMMIT},${env.BRANCH_NAME},$BUILD_STATUS,<b>'HH:mm:ss'</b>")
            cleanWs()
        }
         success {
            echo 'I succeeeded!'
        }
        unstable {
            echo 'I am unstable :/'
        }
        failure {
            echo 'I failed :('
        }
        changed {
            echo 'Things were different before...'
        }
        
    }
	
}