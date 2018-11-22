pipeline {
	agent none

	stages {
		stage('Build artifact') {
			steps {
				echo 'Building artifact.'
				mvn clean install -T 1.5C -DskipTests
			}
		}

		stage('Testing') {
			steps {
				echo 'Testing artifact.'
				mvn verify
			}
		}

		stage('Deploying artifact.') {
			steps {
				echo 'This build will not be deployed.'
			}
		}
	}
}
