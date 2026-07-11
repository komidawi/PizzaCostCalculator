As there were problems with old sonar plugin, here's the way to invoke taking report manually with binary:

```shell
sonar-scanner.bat `
  -D"sonar.organization=komidawi-sonar" `
  -D"sonar.projectKey=komidawi_PizzaCostCalculator" `
  -D"sonar.coverage.jacoco.xmlReportPaths=app/build/reports/jacoco/testDebugUnitTestCoverage/testDebugUnitTestCoverage.xml" `
  -D"sonar.coverage.exclusions=**/test/**,**/sharedTest/**,**/androidTest/**,**/R.class,**/BuildConfig.*,**/Manifest*.*,**/*Test*.*,**/com/example/databinding/*,**/com/example/generated/callback/*,**/*Dto*.*,**/android/databinding/*,**/androidx/databinding/*,**/di/module/*,**/*MapperImpl*.*,**/BuildConfig.*,**/*Component*.*,**/*BR*.*,**/Manifest*.*,**/*Companion*.*,**/*Module.*,**/*Dagger*.*,**/*MembersInjector*.*,**/*Extensions*.*,**/InputMethodManagerLeaks.*,**/*_Factory*.*,**/*_Provide*Factory*.*"
```
