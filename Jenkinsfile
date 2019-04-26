pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
		script {
			sh './gradlew -v'
			sh './gradlew assemble'
		}
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
