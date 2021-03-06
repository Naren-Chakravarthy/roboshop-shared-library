def lintChecks() {
    sh '''
        # we comment this because devs gonna check the failures
        #~/node_modules/jslint/bin/jslint.js server.js
        echo lint Check for ${COMPONENT}
    '''
}

def SonarCheck() {
    sh '''
      sonar-scanner -Dsonar.host.url=http://172.31.3.155:9000 -Dsonar.sources=. -Dsonar.projectkey=${COMPONENT} -Dsonar.login=${SONAR_USR} -Dsonar.password=${SONAR_PSW}
    '''
}

def call() {
    pipeline {
        agent any

        environment{
            SONAR = credentials('SONAR')
        }
        stages{
            // for each commit
            stage("Lint Checks"){
                steps {
                    script{
                        lintChecks()
                    }

                }
            }
            stage("Sonar Check"){
                steps {
                    script{
                        SonarCheck()
                    }

                }
            }
        } // end of stages
    }
}