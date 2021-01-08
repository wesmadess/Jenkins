job('ejemplo2-job-DSL'){
	description('Job DSL de ejemplo para Jenkins')
  scm {
    git('https://github.com/macloujulian/jenkins.job.parametrizado.git','main') { node ->
      node /gitConfigName('wesmadess')
      node /gitConfigEmail('wesmadess@gmail.com')    
    }
  }
  parameters {
  	stringParam('nombre', defaultValue = 'Jose Miguel', description = 'Parametro de cadena para el Job')
	choiceParam('planeta', ['Mercurio','Venus','Tierra'])    
    booleanParam('agente', false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
  	shell("bash jobscript.sh")
  }
  publishers {
  	mailer('wesmadess@gmail.com', true, true)
    slackNotifier {
		notifyAborted(true)
		notifyEveryFailure(true)
		notifyNotBuilt(false)
		notifyUnstable(false)
		notifyBackToNormal(true)
		notifySuccess(false)
		notifyRepeatedFailure(false)
		startNotification(false)
		includeTestSummary(false)
		includeCustomMessage(false)
		customMessage(null)
		sendAs(null)
		commitInfoChoice('NONE')
		teamDomain(null)
		authToken(null)
    }
  }
}
