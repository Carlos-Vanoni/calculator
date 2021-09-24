pipeline {
    
    agent any

    stages {
        
        
        stage('Checkout repository') {
            steps {
                git branch: 'main', url: "https://github.com/Carlos-Vanoni/calculator.git"
            }
        }
        stage('Get permission') {
            steps {
                sh "chmod +x -R ${env.WORKSPACE}"
            }
        }
         stage('Test application') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build application') {
            steps {
                sh './gradlew build'
            }
        }
        stage('Depoly') {
            steps {
                rtServer (
                    id: 'Artifactory',
                    url: 'http://192.168.0.6:8082/artifactory',
                    username: 'carlos-vanoni',
                    password: 'Jfrog123',
                )
                rtUpload (
                    serverId: 'Artifactory',
                    spec: '''{
                          "files": [
                            {
                              "pattern": "build/libs/*.war",
                              "target": "calculator"
                            }
                         ]
                    }''')
            }
        }
         stage('build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "Artifactory"
                    )
            }
        }
    }
}
