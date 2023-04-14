@ui @HealthCheck

Feature: E-commerce Project Web Site Health Check

Background: navigation on the landing page

            Given user navigate to the home application URL

@HomePage
Scenario: User successfully visit on the home page of the application

When  user is on application landing page
Then  Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

@SearchProd
Scenario: user navigate to the landing page and search the product

When  user is on application landing page
And   Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
Then  user search for the product "majestic solitaire diamond ring"
And   "majestic solitaire diamond ring" displayed after the search

@SearchProd1
Scenario: user click on searched product and select the size of it

And   user is on application landing page
When  Application title is "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
And   user search for the product "majestic solitaire diamond ring"
And   "Majestic Solitaire Diamond Ring" displayed after the search
Then  user click on the searched product and validate the title of the page "Majestic Solitaire Diamond Ring"
And   user select the size and validate it "8"
Then  see the notification that "Price updated"


@Footerlinks
Scenario: user scrool down the page and see the about us section in a footer links

When user scroll down the page
And See the "ABOUT US" Section
Then See the list of link below anbout us

|     About Our Company    |
|     Terms and Conditions |
|     Privacy Policy       |
|     FAQ                  |
|     Offers T&Cs          |
|     Customer Reviews     |

@FollowUsSection
Scenario Outline: user scroll down the page and validate the all follow us link

When user scroll down the page
And See the "FOLLOW US" Section
And Click on the "<MediaLink>"
Then validate the All social media links With Expected "<result>" and "<pagetext>"

Examples:
|     MediaLink     |     result        |          pagetext             |
|     facebook      | canderejewellery  |  Candere by Kalyan Jewellers  |
|     instagram     | canderejewellery  |       canderejewellery        |
|     twitter       |  canderebyKalyan  |  Candere By Kalyan jewellers  |


