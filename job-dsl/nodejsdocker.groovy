job('VidlyDocker') {
    scm {
        git('https://github.com/RanSolo/vidly.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('RandallDSL')
            node / gitConfigEmail('ransolo@me.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('ransolo/nodeapp-with-jenkins')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
