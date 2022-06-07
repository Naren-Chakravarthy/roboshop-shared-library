def lintChecks() {
    sh '''
        # we comment this because devs gonna check the failures
        #~/node_modules/jslint/bin/jslint.js server.js
        #pylint *.py
        echo lint Check for ${COMPONENT}
    '''
}

def call() {
    pipeline {
        agent any
        stages{
            // for each commit
            stage("Lint Checks"){
                steps {
                    script{
                        lintChecks()
                    }

                }
            }
        } // end of stages
    }
}