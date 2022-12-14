Demo Application for a coding challenge with the following requirements:

1. The application should contain one screen with a search field and a list of repositories;
2. After the user inputs, the search query in the text field application should query https://api.github.com/search/repositories for a list of repositories. API details https://docs.github.com/en/rest/reference/search ;
3. Search request result should contain 30 items and support pagination; (NOT IMPLEMENTED YET)
4. List item should have repository info: Owner avatar image, Owner name, Repository name, Repository title, Repository description, Repository URL;
6. The application should support phone and tablet;
7. The application should support portrait and landscape layout;
8. Tech requirements: MVVM, Kotlin, Coroutines, Retrofit, Hilt, Min SDK 23
9. The app should be obfuscated with R8 (NOT TESTED)

