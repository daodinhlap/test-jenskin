pipeline {
    agent any
    stages {
        stage('Example') {
            steps {
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                sh 'curl -X POST -H 'Content-type: application/json' --data '{"text": "Application: hellolololol" }' https://hooks.slack.com/services/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'
            }
        }
    }
}