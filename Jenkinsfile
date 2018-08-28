pipeline {
    agent any

    stages {
        stage ('Compile Stage') {

            steps {
                withMaven(maven : 'Maven') {
                    sh 'mvn clean install'
                }
            }
        }
    }
    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
        success {
            echo 'I succeeeded!'
           sh 'curl -X POST -H 'Content-type: application/json' --data '{"text": "Application: I succeeeded ! " }' https://hooks.slack.com/services/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;
        }
        unstable {
            echo 'I am unstable :'
        }
        failure {
            echo 'I failed :'
           sh 'curl -X POST -H 'Content-type: application/json' --data '{"text": "Application: I faild ! " }' https://hooks.slack.com/services/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'
            
        }
        changed {
            echo 'Things were different before...'
        }
    } 
}
}