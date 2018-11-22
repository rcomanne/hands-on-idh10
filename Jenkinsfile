pipeline {
	agent none

	tools {
		maven 'Maven 3.6.0'
		jdk 'jdk10'
	}

	stages {
		stage('Build artifact') {
			steps {
				echo 'Building artifact.'
				sh 'mvn clean install -T 1.5C -DskipTests'
			}
		}

		stage('Testing') {
			steps {
				echo 'Testing artifact.'
				sh 'mvn verify'
			}
		}

		stage('Deploying artifact.') {
			steps {
				echo 'This build will not be deployed.'
			}
		}
	}
}
