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
                stage('Build Backend') {  // Pas "Back-end Image"
                    steps {
                        dir('dailymon') {
                            sh '''
                                # Compiler avec Maven sur l'hôte VPS
                                mvn clean package -DskipTests
                                # Ensuite créer l'image Docker
                                docker build -t dailymon:latest .
                            '''
                        }
                    }
                }
                stage('Build Frontend') {
                    steps {
                        dir('dailyvue') {
                            sh 'docker build -t dailyvue:latest .'
                        }
                    }
                }
            }
        }
        
        stage('Deploy') {
            steps {
                sh '''
                    cd /home/debian/dailymon/
                    docker-compose --env-file .env.prod down
                    docker-compose --env-file .env.prod up -d
                '''
            }
        }
    }
}