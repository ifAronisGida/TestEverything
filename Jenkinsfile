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
            createIssueTest = 'TestCreateIssueUsingPOM'

        }

        stages {

            stage ("Run smoke test") {
                steps {
                    runTest(loginTest)
                }
            }
            stage ("Run all tests") {
                steps {
                    runTest(createIssueTest)
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