pipeline {
    agent any
    
    stages {
        stage('Pull Repos') {
            parallel {
                stage('Pull Frontend') {
                    steps {
                        dir('dailyvue') {
                            git branch: 'main', 
                                url: 'https://github.com/brieuc/dailyvue.git'
                        }
                    }
                }
                stage('Pull Backend') {
                    steps {
                        dir('dailymon') {
                            git branch: 'main', 
                                url: 'https://github.com/brieuc/dailymon.git'
                        }
                    }
                }
            }
        }
        
        stage('Build Images') {
            parallel {
                stage('Build Frontend') {
                    steps {
                        dir('dailyvue') {
                            sh 'docker build -t dailyvue:latest .'
                        }
                    }
                }
                stage('Build Backend') {
                    steps {
                        dir('dailymon') {
                            sh 'docker build -t dailymon:latest .'
                        }
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sh '''
                    cd /dailymon/
                    docker-compose down --env-file .env.prod
                    docker-compose up -d --env-file .env.prod
                '''
            }
        }
    }
}