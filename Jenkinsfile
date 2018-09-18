properties([
        buildDiscarder(
                logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '20')),
        disableConcurrentBuilds(),
        pipelineTriggers([])
])

node
{
    try
    {
        startBuildNotification()
        stage('Checkout')
        {
            checkout scm
            sh 'git clean -fdx'
        }

        stage('Build and test')
        {
            try
            {
                sh "./gradlew clean build"
            }
            finally
            {
                junit testResults: 'build/test-results/**/*.xml'
                if (currentBuild.result == 'UNSTABLE')
                {
                    currentBuild.result = 'FAILURE'
                    throw new RuntimeException("Tests failed!")
                }
            }
        }

    }
    catch (e)
    {
        slackSend color: 'danger', channel: "#builds", message: "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - Failed!\nCheck console output at ${env.BUILD_URL} to view the results."
        currentBuild.result = 'FAILURE'
        throw e
    }
}