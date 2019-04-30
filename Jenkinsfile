def runTest(testName) {


    sh "mvn -e -Dbrowser=${browser} -Duser=${user} -Dpassword=${password} -DtestUrl=${testUrl} -Dtest=" + testName + " clean test"
}

    pipeline {

        agent any

        environment {
            user = 'user14'
            password = 'CoolCanvas19.'
            testUrl = 'http://selenium:CoolCanvas19.@seleniumhub.codecool.codecanvas.hu:4444/wd/hub'
            browser = 'chrome'
            loginTest = 'TestLoginUsingPOM'

        }

        stages {
            stage ("Get source") {
                steps {
                    git branch: 'CI', url: 'https://github.com/ifAronisGida/TestEverything.git'
                }
            }


            stage ("Run smoke test") {
                steps {
                    runTest(loginTest)
                }
            }
    }

    post {
        always {
            junit 'target/surefire-reports/*.xml'
            cleanWs()
        }
    }
}