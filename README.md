# Foodys - Recipe Search App

## Experience

Building this app was an exciting experience as it involved integrating Spoonacular API to fetch recipes and provide users with useful information about food. Using Jetpack Compose for the UI was a new experience, and it allowed me to experiment with a more declarative approach to UI design.

## Features

- **Recipe Search**: Users can search for recipes based on ingredients, calories, or protein levels.
- **Popular Recipes**: Display a list of popular recipes that the user can browse.
- **Recipe Details**: Clicking on a recipe displays more details, including ingredients and instructions.
- **Error Handling**: If there is an issue fetching data from the API, the app gracefully handles errors and shows an appropriate error message.
- **Skeleton Loading**: While data is being loaded, skeleton views are shown to improve the user experience.
  
## Challenges

- **API Integration**: One challenge I faced was ensuring that the parameters passed to the API were correct. Initially, I faced issues with missing or incorrect parameters which caused the API to fail to return data.
- **UI Customization**: Another challenge was customizing Jetpack Compose components for a smooth UI experience.
- **Error Handling**: Implementing a proper error handling mechanism to ensure users aren't left confused when something goes wrong was tricky but essential.
  
## Assumptions

- **API Limitations**: The app assumes the Spoonacular API will always respond with data in the expected format. In case of changes to the API, further modifications may be required.
- **Internet Connection**: The app assumes that users will have a stable internet connection for fetching recipes. If no internet is available, an appropriate error message is shown.
- **Recipe Data**: The app assumes that all recipes returned from the API will have a title and image. If these are missing, it will still display a placeholder.

---

## How to Run the App

1. Clone the repository:

2. Open the project in Android Studio.

3. Run the app on an Android emulator or a physical device.

4. Make sure you have the API key for Spoonacular. If not, sign up at [Spoonacular API](https://spoonacular.com/food-api) and get your key.

## Tech Stack

- Kotlin
- Jetpack Compose
- Koin (Dependency Injection)
- Retrofit for API integration
- Spoonacular API
- Firebase (Optional for further features like saving favorites)
