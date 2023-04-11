@ui @HealthCheck

Feature: E-commerce Project Web Site Health Check

@HomePage
Scenario: User successfully visit on the home page of the application

Given user navigate to the home application URL
When  user is on application landing page
Then  Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

@SearchProd
Scenario: user navigate to the landing page and search the product

Given user navigate to the home application URL
When  user is on application landing page
And   Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
Then  user search for the product "majestic solitaire diamond ring"
And   "majestic solitaire diamond ring" displayed after the search

@SearchProda
Scenario: user click on searched product and select the size of it

Given user navigate to the home application URL
And   user is on application landing page
When  Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
And   user search for the product "majestic solitaire diamond ring"
And   "majestic solitaire diamond ring" displayed after the search
Then  user click on the searched product and validate the title of the page "Majestic Solitaire Diamond Ring"
And   user select the size and validate it
Then  see the notification that "Price updated"
