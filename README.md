# Talent App Store demo application
A simple demo app showing how to build a SpringBoot app that produces the following TalentAppStore APIs to cater for:
 - Install (`POST /tas/core/tenants`) 
 - Uninstall (`DELETE /tas/core/tenants/{tenant}`)
 - appStatus (`GET /t/{tenant}/tas/devs/tas/appStatus`) 
 
 See the developer documentation for more information
 https://devdocs.talentappstore.com/doc/home.html

## Setup
###### Requirements
- Java (8+)
- Gradle
- ngrok (https://ngrok.com)

###### App Setup within https://developer.talentappstore.com
- Sign up to https://developer.talentappstore.com
- Create a developer.
- Define an app within that developer.
- Copy/take note of your apps install token. You'll need this for testing later.
- Copy your apps shortcode and HMAC secret key into `application.properties` in this project (`tas.short.code` & `tas.app.secret`)
- Define that your newly created app produces the `/appStatus` API on the APIs page.
- On the Single sign on tab set the principal type to `user`.

###### Run the application
- Run the application `./gradlew bootRun`
- Expose the app with ngrok `./ngrok http 8080`
- Note down the address ngrok has exposed and copy it into your app config in developer.talentappstore.com. (Back end server field, under the Tazzy menu)

###### Testing
This will test your app is running and responding as expected.
- Sign up to https://www.talentappstore.com
- Create a tenant
- Within your tenant click the top right menu with your tenant name and choose 'Install from token'.
- Enter the install token you noted during setup and press the install button.
- You should now see a log message in the running app that your tenant has installed your application!
- You should also see your app has appeared in your installed app list with a 'Setup required banner'
